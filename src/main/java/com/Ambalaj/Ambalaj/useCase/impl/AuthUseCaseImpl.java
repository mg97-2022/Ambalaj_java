package com.Ambalaj.Ambalaj.useCase.impl;

import com.Ambalaj.Ambalaj.dto.CompanySignupRequestDTO;
import com.Ambalaj.Ambalaj.dto.LoginRequestDTO;
import com.Ambalaj.Ambalaj.mapper.AppUserMapper;
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
    public void login(LoginRequestDTO loginRequest) {
        authService.login(loginRequest);
    }

    @Override
    public void confirmEmail(String confirmationToken) {
        authService.confirmEmail(confirmationToken);
    }
}
