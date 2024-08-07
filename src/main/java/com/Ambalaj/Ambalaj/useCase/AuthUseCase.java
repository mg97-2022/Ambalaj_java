package com.Ambalaj.Ambalaj.useCase;

import com.Ambalaj.Ambalaj.dto.CompanySignupRequestDTO;
import com.Ambalaj.Ambalaj.dto.LoginRequestDTO;

public interface AuthUseCase {
    void companySignup(CompanySignupRequestDTO companySignupRequestDTO);

    void login(LoginRequestDTO loginRequest);

    void confirmEmail(String confirmationToken);
}
