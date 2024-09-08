package com.Ambalaj.Ambalaj.service;

import com.Ambalaj.Ambalaj.model.CompanyEntity;
import com.Ambalaj.Ambalaj.model.CompanyProductsNumberToCreateEntity;

public interface CompanyProductsNumberToCreateService {
    void addCompanyProductsNumberToCreate(CompanyProductsNumberToCreateEntity companyProductsNumberToCreate);

    CompanyProductsNumberToCreateEntity getByCompany(CompanyEntity companyEntity);

    void updateCompanyProductsNumberToCreate(CompanyProductsNumberToCreateEntity companyProductsNumberToCreate);
}
