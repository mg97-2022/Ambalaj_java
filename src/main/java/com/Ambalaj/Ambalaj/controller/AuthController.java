package com.Ambalaj.Ambalaj.controller;

import com.Ambalaj.Ambalaj.dto.LoginRequestDTO;
import com.Ambalaj.Ambalaj.dto.LoginResponseDTO;
import com.Ambalaj.Ambalaj.dto.SignupRequestDTO;
import com.Ambalaj.Ambalaj.service.AuthService;
import com.Ambalaj.Ambalaj.utils.ResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup/company")
    public ResponseEntity<ResponseDTO> companySignup(@Valid @RequestBody SignupRequestDTO signupRequestDTO) {
        authService.companySignup(signupRequestDTO);
        ResponseDTO response = ResponseDTO.builder().message(
                        "Your account has been created successfully. Please check your email to verify and activate your account.")
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO loginResponse = authService.login(loginRequestDTO);
        ResponseDTO response = ResponseDTO.builder().data(loginResponse).build();
        return ResponseEntity.ok(response);
    }
}