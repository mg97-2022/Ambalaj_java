package com.Ambalaj.Ambalaj.mapper;

import com.Ambalaj.Ambalaj.dto.CompanySignupCompanyDTO;
import com.Ambalaj.Ambalaj.model.CompanyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyEntity toEntityFromSignup(CompanySignupCompanyDTO companySignupCompanyDTO);
}
