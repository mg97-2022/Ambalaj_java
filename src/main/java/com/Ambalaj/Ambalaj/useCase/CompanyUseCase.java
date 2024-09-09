package com.Ambalaj.Ambalaj.useCase;

import com.Ambalaj.Ambalaj.dto.CompanyDTO;

public interface CompanyUseCase {
    CompanyDTO getCompanyByName(String companyName);
}
