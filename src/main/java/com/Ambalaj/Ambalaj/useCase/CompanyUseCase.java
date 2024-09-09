package com.Ambalaj.Ambalaj.useCase;

import com.Ambalaj.Ambalaj.dto.CompanyDTO;
import com.Ambalaj.Ambalaj.dto.PaginatedDTO;

import java.util.List;

public interface CompanyUseCase {
    CompanyDTO getCompanyByName(String companyName);

    PaginatedDTO<CompanyDTO> getPaginatedCompanies(
            Integer page, Integer pageSize, String search, List<Long> cities, List<Integer> countries,
            List<Long> industries, Long category);
}
