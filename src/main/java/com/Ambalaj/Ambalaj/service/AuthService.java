package com.Ambalaj.Ambalaj.service;

import com.Ambalaj.Ambalaj.dto.LoginRequestDTO;
import com.Ambalaj.Ambalaj.dto.LoginResponseDTO;
import com.Ambalaj.Ambalaj.dto.SignupRequestDTO;

public interface AuthService {
    void companySignup(SignupRequestDTO signupRequestDTO);

    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}
