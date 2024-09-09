package com.Ambalaj.Ambalaj.service;

import com.Ambalaj.Ambalaj.model.CompanyEntity;

public interface CompanyService extends AppUserExtraDetailsService<CompanyEntity> {
    void addCompany(CompanyEntity companyEntity);

    CompanyEntity getCompanyByName(String companyName);
}
