package com.Ambalaj.Ambalaj.service;

import com.Ambalaj.Ambalaj.exception.NotFoundException;
import com.Ambalaj.Ambalaj.model.AppUserEntity;
import com.Ambalaj.Ambalaj.model.CompanyEntity;

public interface CompanyService {
    void addCompany(CompanyEntity companyEntity);

    CompanyEntity findByAppUser(AppUserEntity appUser) throws NotFoundException;
}
