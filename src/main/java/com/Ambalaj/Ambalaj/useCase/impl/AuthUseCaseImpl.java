package com.Ambalaj.Ambalaj.useCase.impl;

import com.Ambalaj.Ambalaj.dto.ClientSignupRequestDTO;
import com.Ambalaj.Ambalaj.dto.CompanySignupRequestDTO;
import com.Ambalaj.Ambalaj.dto.LoginRequestDTO;
import com.Ambalaj.Ambalaj.dto.LoginResponseDTO;
import com.Ambalaj.Ambalaj.mapper.AppUserMapper;
import com.Ambalaj.Ambalaj.mapper.ClientMapper;
import com.Ambalaj.Ambalaj.mapper.CompanyMapper;
import com.Ambalaj.Ambalaj.model.*;
import com.Ambalaj.Ambalaj.service.AuthService;
import com.Ambalaj.Ambalaj.useCase.AuthUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthUseCaseImpl implements AuthUseCase {
    private final AuthService authService;
    private final AppUserMapper appUserMapper;
    private final CompanyMapper companyMapper;
    private final ClientMapper clientMapper;

    @Override
    public void companySignup(CompanySignupRequestDTO companySignupDTO) {
        AppUserEntity appUser = appUserMapper.toEntityFromSignup(companySignupDTO.getUser());
        CompanyEntity company = companyMapper.toEntityFromSignup(companySignupDTO.getCompany());
        company.setAppUser(appUser);
        authService.companySignup(company, companySignupDTO.getCompany().getCityId(),
                                  companySignupDTO.getCompany().getCategoriesId(),
                                  companySignupDTO.getCompany().getIndustriesId());
    }

    @Override
    public void clientSignup(ClientSignupRequestDTO clientSignupDTO) {
        AppUserEntity appUser = appUserMapper.toEntityFromSignup(clientSignupDTO.getUser());
        ClientEntity client = clientMapper.toEntityFromSignup(clientSignupDTO.getClient());
        client.setAppUser(appUser);
        authService.clientSignup(client);
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO, boolean isWebsite) {
        return authService.login(loginRequestDTO, isWebsite);
    }

    @Override
    public void confirmEmail(String confirmationToken) {
        authService.confirmEmail(confirmationToken);
    }

    @Override
    public void forgotPassword(String appUserEmail) {
        authService.forgotPassword(appUserEmail);
    }

    @Override
    public void resetPassword(String newPassword, String resetToken) {
        authService.resetPassword(newPassword, resetToken);
    }

    @Override
    public void resendConfirmationEmail(String appUserEmail) {
        authService.resendConfirmationEmail(appUserEmail);
    }
}
