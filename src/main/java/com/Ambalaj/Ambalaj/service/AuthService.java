package com.Ambalaj.Ambalaj.service;

import com.Ambalaj.Ambalaj.dto.LoginRequestDTO;
import com.Ambalaj.Ambalaj.model.CompanyEntity;

import java.util.List;

public interface AuthService {
    void companySignup(CompanyEntity company, Long CompanyCityId, List<Long> companyCategoryIds,
                       List<Long> companyIndustryIds);

    void login(LoginRequestDTO loginRequestDTO);

    void confirmEmail(String confirmationToken);
}
