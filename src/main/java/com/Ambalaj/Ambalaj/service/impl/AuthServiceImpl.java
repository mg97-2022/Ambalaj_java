package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.dto.LoginRequestDTO;
import com.Ambalaj.Ambalaj.enums.AppUserRole;
import com.Ambalaj.Ambalaj.model.*;
import com.Ambalaj.Ambalaj.service.*;
import com.Ambalaj.Ambalaj.utils.email.EmailService;
import com.Ambalaj.Ambalaj.utils.email.EmailTemplates;
import com.Ambalaj.Ambalaj.exception.CustomException;
import com.Ambalaj.Ambalaj.security.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final CityService cityService;
    private final CategoryService categoryService;
    private final IndustryService industryService;
    private final PasswordEncoder passwordEncoder;
    private final CompanyService companyService;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailService emailService;
    private final EmailTemplates emailTemplates;
    private final AppUserService appUserService;

    @Value("${spring.app.clientUrl}")
    private String clientUrl;

    @Override
    @Transactional
    public void companySignup(CompanyEntity company, Long companyCityId, List<Long> companyCategoryIds,
                              List<Long> companyIndustryIds) {
        CityEntity city = cityService.getCity(companyCityId);
        company.setCity(city);
        List<CategoryEntity> categories = categoryService.getCategoriesByIds(companyCategoryIds);
        company.setCategories(categories);
        List<IndustryEntity> industries = industryService.getIndustriesByIds(companyIndustryIds);
        company.setIndustries(industries);
        company.getAppUser().setPassword(passwordEncoder.encode(company.getAppUser().getPassword()));
        company.getAppUser().setRole(AppUserRole.COMPANY);
        companyService.addCompany(company);
        String token = saveConfirmationToken(company.getAppUser());
        sendConfirmationEmail(company.getAppUser().getEmail(), company.getName(), token);
    }

    @Override
    public void login(LoginRequestDTO loginRequestDTO) {
//        // 1) Check if password and email matches
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));
//        } catch (AuthenticationException e) {
//            throw new NotFoundException("Invalid email or password.");
//        }
//        // 2) Get the user details
//        AppUserEntity user = appUserService.findUserByEmail(loginRequestDTO.getEmail())
//                .orElseThrow(() -> new NotFoundException("User not found."));
//        // 3) Generate the user access token
//        String accessToken = jwtUtil.createToken(loginRequestDTO.getEmail());
//        // 4) Get login response DTO
//        AppUserDTO userDetails =
//                AppUserDTO.builder().id(user.getId()).email(user.getEmail())
////                        .firstName(user.getFirstName())
////                        .lastName(user.getLastName())
//                        .role(user.getRole().name()).build();
//        return LoginResponseDTO.builder().accessToken(accessToken).user(userDetails).build();
    }

    @Override
    @Transactional
    public void confirmEmail(String confirmationToken) {
        ConfirmationTokenEntity token = confirmationTokenService.getConfirmationToken(confirmationToken);
        if (token.getAppUser().getEnabled())
            throw new CustomException("Your email is already confirmed.", HttpStatus.BAD_REQUEST);
        if (token.getExpiresAt().isBefore(LocalDateTime.now()))
            throw new CustomException("Token is expired.", HttpStatus.BAD_REQUEST);
        token.setConfirmedAt(LocalDateTime.now());
        confirmationTokenService.saveConfirmationToken(token);
        token.getAppUser().setEnabled(true);
        appUserService.updateUser(token.getAppUser());
    }

    @Override
    @Transactional
    public void forgotPassword(String appUserEmail) {
        AppUserEntity user = appUserService.findUserByEmail(appUserEmail);
        verifyAccountStatus(user);
        String token = generateToken();
        user.setResetPasswordToken(token);
        user.setResetPasswordTokenExpiry(LocalDateTime.now().plusMinutes(15));
        appUserService.updateUser(user);
        sendResetPasswordEmail(user.getEmail(), user.getUsername(), token);
    }

    @Override
    public void resetPassword(String newPassword, String resetToken) {
        AppUserEntity user = appUserService.findUserByResetPasswordToken(resetToken);
        if (user.getResetPasswordTokenExpiry().isBefore(LocalDateTime.now()))
            throw new CustomException("Token is expired.", HttpStatus.BAD_REQUEST);
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setPasswordChangedAt(LocalDateTime.now());
        user.setResetPasswordToken(null);
        user.setResetPasswordTokenExpiry(null);
        appUserService.updateUser(user);
    }

    @Override
    @Transactional
    public void resendConfirmationEmail(String appUserEmail) {
        AppUserEntity user = appUserService.findUserByEmail(appUserEmail);
        if (user.getEnabled()) throw new CustomException("Your email is already confirmed.", HttpStatus.BAD_REQUEST);
        String token = saveConfirmationToken(user);
        sendConfirmationEmail(appUserEmail, appUserEmail, token);
    }

    /******************** HELPER METHODS ********************/
    private String generateToken() {
        return UUID.randomUUID().toString();
    }

    private String saveConfirmationToken(AppUserEntity appUserEntity) {
        String token = generateToken();
        ConfirmationTokenEntity confirmationToken =
                ConfirmationTokenEntity.builder().token(token).createdAt(LocalDateTime.now())
                        .expiresAt(LocalDateTime.now().plusMinutes(15)).appUser(appUserEntity).build();
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }

    private void sendConfirmationEmail(String email, String userName, String token) throws CustomException {
        String subject = "Confirm your email";
        // This is the page in Front-End that the user will be redirected to for verification
        String link = clientUrl + "/confirm-email?confirmationToken=" + token;
        String body = emailTemplates.emailTemplate(userName, link);
        emailService.send(email, body, subject);
    }

    private void verifyAccountStatus(AppUserEntity appUser) {
        if (!appUser.getEnabled()) throw new CustomException("Please verify your email first.", HttpStatus.BAD_REQUEST);
        if (appUser.getLocked()) throw new CustomException("Your account is locked", HttpStatus.BAD_REQUEST);
    }

    private void sendResetPasswordEmail(String email, String userName, String token) throws CustomException {
        String subject = "Reset your Password";
        // This is the page in Front-End that the user will be redirected to for resetting password
        String link = clientUrl + "/reset-password?resetToken=" + token;
        String body = emailTemplates.emailTemplate(userName, link);
        emailService.send(email, body, subject);
    }
}
