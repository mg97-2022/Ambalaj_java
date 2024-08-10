package com.Ambalaj.Ambalaj.useCase;

import com.Ambalaj.Ambalaj.dto.CompanySignupRequestDTO;
import com.Ambalaj.Ambalaj.dto.LoginRequestDTO;
import com.Ambalaj.Ambalaj.dto.LoginResponseDTO;

public interface AuthUseCase {
    void companySignup(CompanySignupRequestDTO companySignupRequestDTO);

    LoginResponseDTO login(LoginRequestDTO loginRequestDTO, boolean isWebsite);

    void confirmEmail(String confirmationToken);

    void forgotPassword(String appUserEmail);

    void resetPassword(String newPassword, String resetToken);

    void resendConfirmationEmail(String appUserEmail);
}
