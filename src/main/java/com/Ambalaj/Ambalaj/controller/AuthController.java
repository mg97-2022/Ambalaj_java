package com.Ambalaj.Ambalaj.controller;

import com.Ambalaj.Ambalaj.dto.*;
import com.Ambalaj.Ambalaj.useCase.AuthUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthUseCase authUseCase;

    @PostMapping("/signup/company")
    public ResponseEntity<ResponseDTO<Void>> companySignup(
            @Valid @RequestBody CompanySignupRequestDTO companySignupDTO) {
        authUseCase.companySignup(companySignupDTO);
        return ResponseEntity.ok(ResponseDTO.<Void>builder().message(
                        "Your account has been created successfully. Please check your email to verify and activate your account.")
                                         .build());
    }

    @PostMapping("/signup/client")
    public ResponseEntity<ResponseDTO<Void>> clientSignup(
            @Valid @RequestBody ClientSignupRequestDTO clientSignupRequestDTO) {
        authUseCase.clientSignup(clientSignupRequestDTO);
        return ResponseEntity.ok(ResponseDTO.<Void>builder().message(
                        "Your account has been created successfully. Please check your email to verify and activate your account.")
                                         .build());
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<LoginResponseDTO>> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO loginResponse = authUseCase.login(loginRequestDTO, true);
        return ResponseEntity.ok(ResponseDTO.<LoginResponseDTO>builder().data(loginResponse).build());
    }

    @GetMapping("/confirm-email")
    public ResponseEntity<ResponseDTO<Void>> confirmEmail(@RequestParam String confirmationToken) {
        authUseCase.confirmEmail(confirmationToken);
        return ResponseEntity.ok(
                ResponseDTO.<Void>builder().message("Your account has been verified successfully.").build());
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ResponseDTO<Void>> forgotPassword(
            @Valid @RequestBody ForgotPasswordRequestDTO forgotPasswordRequestDTO) {
        authUseCase.forgotPassword(forgotPasswordRequestDTO.getEmail());
        return ResponseEntity.ok(
                ResponseDTO.<Void>builder().message("Please check your email to reset password.").build());
    }

    @PatchMapping("/reset-password")
    public ResponseEntity<ResponseDTO<Void>> resetPassword(
            @Valid @RequestBody ResetPasswordRequestDTO resetPasswordRequestDTO, @RequestParam String resetToken) {
        authUseCase.resetPassword(resetPasswordRequestDTO.getPassword(), resetToken);
        return ResponseEntity.ok(ResponseDTO.<Void>builder().message("Password reset successfully.").build());
    }

    @PostMapping("/resend-confirmation-email")
    public ResponseEntity<ResponseDTO<Void>> resendConfirmationEmail(
            @Valid @RequestBody ResendConfirmationEmailDTO resendConfirmationEmail) {
        authUseCase.resendConfirmationEmail(resendConfirmationEmail.getEmail());
        return ResponseEntity.ok(ResponseDTO.<Void>builder().message("Confirmation email sent successfully.").build());
    }

    @PostMapping("/login/dashboard")
    public ResponseEntity<ResponseDTO<LoginResponseDTO>> dashboardLogin(
            @Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO loginResponse = authUseCase.login(loginRequestDTO, false);
        return ResponseEntity.ok(ResponseDTO.<LoginResponseDTO>builder().data(loginResponse).build());
    }
}