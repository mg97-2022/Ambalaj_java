package com.Ambalaj.Ambalaj.useCase.impl;

import com.Ambalaj.Ambalaj.dto.CompanyDTO;
import com.Ambalaj.Ambalaj.dto.PaginatedDTO;
import com.Ambalaj.Ambalaj.mapper.CompanyMapper;
import com.Ambalaj.Ambalaj.model.CompanyEntity;
import com.Ambalaj.Ambalaj.service.CompanyService;
import com.Ambalaj.Ambalaj.useCase.CompanyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CompanyUseCaseImpl implements CompanyUseCase {
    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    @Override
    public CompanyDTO getCompanyByName(String companyName) {
        CompanyEntity companyEntity = companyService.getCompanyByName(companyName);
        return companyMapper.toAllDetailsDto(companyEntity);
    }

    @Override
    public PaginatedDTO<CompanyDTO> getPaginatedCompanies(
            Integer page, Integer pageSize, String search, List<Long> cities, List<Integer> countries,
            List<Long> industries, Long category) {
        Page<CompanyEntity> pageableCompaniesList =
                companyService.getPaginatedCompanies(page, pageSize, search, cities, countries, industries, category);
        return companyMapper.toPaginatedDto(pageableCompaniesList);
    }
}
