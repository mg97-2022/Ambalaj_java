package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.exception.NotFoundException;
import com.Ambalaj.Ambalaj.model.CompanyEntity;
import com.Ambalaj.Ambalaj.model.CompanyProductsNumberToCreateEntity;
import com.Ambalaj.Ambalaj.repository.CompanyProductsNumberToCreateRepository;
import com.Ambalaj.Ambalaj.service.CompanyProductsNumberToCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyProductsNumberToCreateServiceImpl implements CompanyProductsNumberToCreateService {
    private final CompanyProductsNumberToCreateRepository companyProductsNumberToCreateRepository;

    @Override
    public void addCompanyProductsNumberToCreate(CompanyProductsNumberToCreateEntity companyProductsNumberToCreate) {
        companyProductsNumberToCreateRepository.save(companyProductsNumberToCreate);
    }

    @Override
    public CompanyProductsNumberToCreateEntity getByCompany(CompanyEntity companyEntity) {
        return companyProductsNumberToCreateRepository.findByCompany(companyEntity)
                                                      .orElseThrow(() -> new NotFoundException("Company"));
    }

    @Override
    public void updateCompanyProductsNumberToCreate(CompanyProductsNumberToCreateEntity companyProductsNumberToCreate) {
        companyProductsNumberToCreateRepository.save(companyProductsNumberToCreate);
    }
}
