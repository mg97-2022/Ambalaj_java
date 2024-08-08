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
    public ResponseEntity<ResponseDTO> companySignup(@Valid @RequestBody CompanySignupRequestDTO companySignupDTO) {
        authUseCase.companySignup(companySignupDTO);
        return ResponseEntity.ok(ResponseDTO.builder().message(
                        "Your account has been created successfully. Please check your email to verify and activate your account.")
                                         .build());
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(ResponseDTO.builder().message("message").build());
//        LoginResponseDTO loginResponse = authService.login(loginRequestDTO);
//        ResponseDTO response = ResponseDTO.builder().data(loginResponse).build();
//        return ResponseEntity.ok(response);
    }

    @GetMapping("/confirm-email")
    public ResponseEntity<ResponseDTO> confirmEmail(@RequestParam String confirmationToken) {
        authUseCase.confirmEmail(confirmationToken);
        return ResponseEntity.ok(ResponseDTO.builder().message("Your account has been verified successfully.").build());
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ResponseDTO> forgotPassword(
            @Valid @RequestBody ForgotPasswordRequestDTO forgotPasswordRequestDTO) {
        authUseCase.forgotPassword(forgotPasswordRequestDTO.getEmail());
        return ResponseEntity.ok(ResponseDTO.builder().message("Please check your email to reset password.").build());
    }

    @PatchMapping("/reset-password")
    public ResponseEntity<ResponseDTO> resetPassword(
            @Valid @RequestBody ResetPasswordRequestDTO resetPasswordRequestDTO, @RequestParam String resetToken) {
        authUseCase.resetPassword(resetPasswordRequestDTO.getPassword(), resetToken);
        return ResponseEntity.ok(ResponseDTO.builder().message("Password reset successfully.").build());
    }

    @PostMapping("/resend-confirmation-email")
    public ResponseEntity<ResponseDTO> resendConfirmationEmail(
            @Valid @RequestBody ResendConfirmationEmailDTO resendConfirmationEmail) {
        authUseCase.resendConfirmationEmail(resendConfirmationEmail.getEmail());
        return ResponseEntity.ok(ResponseDTO.builder().message("Confirmation email sent successfully.").build());
    }
}