package com.Ambalaj.Ambalaj.mapper;

import com.Ambalaj.Ambalaj.dto.CompanyDTO;
import com.Ambalaj.Ambalaj.dto.CompanySignupCompanyDTO;
import com.Ambalaj.Ambalaj.model.CompanyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyDTO toDto(CompanyEntity company);

    CompanyEntity toEntityFromSignup(CompanySignupCompanyDTO companySignupCompanyDTO);
}
