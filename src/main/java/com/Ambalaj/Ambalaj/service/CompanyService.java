package com.Ambalaj.Ambalaj.service;

import com.Ambalaj.Ambalaj.model.CompanyEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CompanyService extends AppUserExtraDetailsService<CompanyEntity> {
    void addCompany(CompanyEntity companyEntity);

    CompanyEntity getCompanyByName(String companyName);

    Page<CompanyEntity> getPaginatedCompanies(
            Integer page, Integer pageSize, String search, List<Long> cities, List<Integer> countries,
            List<Long> industries, Long category);
}
