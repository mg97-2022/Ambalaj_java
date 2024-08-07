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
        ResponseDTO response = ResponseDTO.builder().message(
                        "Your account has been created successfully. Please check your email to verify and activate your account.")
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(ResponseDTO.builder().message("message").build());
//        LoginResponseDTO loginResponse = authService.login(loginRequestDTO);
//        ResponseDTO response = ResponseDTO.builder().data(loginResponse).build();
//        return ResponseEntity.ok(response);
    }
}