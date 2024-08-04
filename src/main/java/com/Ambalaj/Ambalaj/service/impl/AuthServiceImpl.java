package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.dto.LoginRequestDTO;
import com.Ambalaj.Ambalaj.dto.LoginResponseDTO;
import com.Ambalaj.Ambalaj.dto.SignupRequestDTO;
import com.Ambalaj.Ambalaj.dto.AppUserDetailsDTO;
import com.Ambalaj.Ambalaj.model.ConfirmationTokenEntity;
import com.Ambalaj.Ambalaj.utils.email.EmailService;
import com.Ambalaj.Ambalaj.utils.email.EmailTemplates;
import com.Ambalaj.Ambalaj.exception.CustomException;
import com.Ambalaj.Ambalaj.exception.EntityAlreadyExistsException;
import com.Ambalaj.Ambalaj.exception.NotFoundException;
import com.Ambalaj.Ambalaj.security.JwtUtil;
import com.Ambalaj.Ambalaj.model.AppUserEntity;
import com.Ambalaj.Ambalaj.enums.AppUserRole;
import com.Ambalaj.Ambalaj.service.AppUserService;
import com.Ambalaj.Ambalaj.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final AppUserService appUserService;
    private final ConfirmationTokenServiceImpl confirmationTokenService;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final EmailTemplates emailTemplates;

    @Value("${spring.app.clientUrl}")
    private String clientUrl;

    @Override
    @Transactional
    public void companySignup(SignupRequestDTO signupRequestDTO) {
        // 1) Check if user already exists
        boolean isUserExists = appUserService.userExistsByEmail(signupRequestDTO.getEmail());
        if (isUserExists) {
            throw new EntityAlreadyExistsException(
                    "User with email [" + signupRequestDTO.getEmail() + "] already exists");
        }
        // 2) Save the user details
        AppUserEntity appUser = AppUserEntity.builder().email(signupRequestDTO.getEmail())
//                .firstName(signupRequestDTO.getFirstName())
//                .lastName(signupRequestDTO.getLastName())
                .password(passwordEncoder.encode(signupRequestDTO.getPassword())).role(AppUserRole.COMPANY).build();
        appUserService.saveUser(appUser);
        // 3) Save the confirmation token for the user
        String token = generateConfirmationToken();
        ConfirmationTokenEntity confirmationToken =
                ConfirmationTokenEntity.builder().token(token).createdAt(LocalDateTime.now())
                        .expiresAt(LocalDateTime.now().plusHours(1)).appUser(appUser).build();
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        // 4) Send the confirmation token with email
//        sendConfirmationEmail(appUser.getEmail(), appUser.getFirstName(), token);
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        // 1) Check if password and email matches
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));
        } catch (AuthenticationException e) {
            throw new NotFoundException("Invalid email or password.");
        }
        // 2) Get the user details
        AppUserEntity user = appUserService.findUserByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found."));
        // 3) Generate the user access token
        String accessToken = jwtUtil.createToken(loginRequestDTO.getEmail());
        // 4) Get login response DTO
        AppUserDetailsDTO userDetails =
                AppUserDetailsDTO.builder().id(user.getId()).email(user.getEmail())
//                        .firstName(user.getFirstName())
//                        .lastName(user.getLastName())
                        .role(user.getRole().name()).build();
        return LoginResponseDTO.builder().accessToken(accessToken).user(userDetails).build();
    }

    /******************** HELPER METHODS ********************/
    private String generateConfirmationToken() {
        return UUID.randomUUID().toString();
    }

    private void sendConfirmationEmail(String email, String userName, String token) throws CustomException {
        String subject = "Confirm your email";
        String link = clientUrl + "?confirmation_token=" + token;
        String body = emailTemplates.emailConfirmation(userName, link);
        emailService.send(email, body, subject);
    }
}
