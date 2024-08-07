package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.model.CompanyEntity;
import com.Ambalaj.Ambalaj.repository.CompanyRepository;
import com.Ambalaj.Ambalaj.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    @Override
    public void addCompany(CompanyEntity company) {
        companyRepository.save(company);
    }
}
