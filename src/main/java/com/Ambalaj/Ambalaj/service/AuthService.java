package com.Ambalaj.Ambalaj.service;

import com.Ambalaj.Ambalaj.dto.LoginRequestDTO;
import com.Ambalaj.Ambalaj.dto.LoginResponseDTO;
import com.Ambalaj.Ambalaj.model.ClientEntity;
import com.Ambalaj.Ambalaj.model.CompanyEntity;

import java.util.List;

public interface AuthService {
    void companySignup(CompanyEntity company, Long CompanyCityId, List<Long> companyCategoryIds,
                       List<Long> companyIndustryIds);

    void clientSignup(ClientEntity client);

    LoginResponseDTO login(LoginRequestDTO loginRequestDTO, boolean isWebsite);

    void confirmEmail(String confirmationToken);

    void forgotPassword(String appUserEmail);

    void resetPassword(String newPassword, String resetToken);

    void resendConfirmationEmail(String appUserEmail);
}
