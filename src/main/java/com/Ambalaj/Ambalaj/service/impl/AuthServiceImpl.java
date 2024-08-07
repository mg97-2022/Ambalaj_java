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
        // 1) Construct company entity
        CityEntity city = cityService.getCity(companyCityId);
        company.setCity(city);
        List<CategoryEntity> categories = categoryService.getCategoriesByIds(companyCategoryIds);
        company.setCategories(categories);
        List<IndustryEntity> industries = industryService.getIndustriesByIds(companyIndustryIds);
        company.setIndustries(industries);
        company.getAppUser().setPassword(passwordEncoder.encode(company.getAppUser().getPassword()));
        company.getAppUser().setRole(AppUserRole.COMPANY);
        // 2) Add company
        companyService.addCompany(company);
        // 3) Save the confirmation token for the user
        String token = saveConfirmationToken(company.getAppUser());
        // 4) Send the confirmation token with email
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
            throw new CustomException("Your account is already enabled.", HttpStatus.BAD_REQUEST);
        if (token.getExpiresAt().isBefore(LocalDateTime.now()))
            throw new CustomException("Token is expired.", HttpStatus.BAD_REQUEST);
        token.setConfirmedAt(LocalDateTime.now());
        confirmationTokenService.saveConfirmationToken(token);
        token.getAppUser().setEnabled(true);
        appUserService.updateUser(token.getAppUser());
    }

    /******************** HELPER METHODS ********************/
    private String generateConfirmationToken() {
        return UUID.randomUUID().toString();
    }

    private String saveConfirmationToken(AppUserEntity appUserEntity) {
        String token = generateConfirmationToken();
        ConfirmationTokenEntity confirmationToken =
                ConfirmationTokenEntity.builder().token(token).createdAt(LocalDateTime.now())
                        .expiresAt(LocalDateTime.now().plusHours(1)).appUser(appUserEntity).build();
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }

    private void sendConfirmationEmail(String email, String userName, String token) throws CustomException {
        String subject = "Confirm your email";
        String link = clientUrl + "/api/v1/auth/confirm-email?confirmationToken=" + token;
        String body = emailTemplates.emailConfirmation(userName, link);
        emailService.send(email, body, subject);
    }
}
