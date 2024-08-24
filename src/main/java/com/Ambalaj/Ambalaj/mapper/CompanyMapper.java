package com.Ambalaj.Ambalaj.mapper;

import com.Ambalaj.Ambalaj.dto.CompanyDTO;
import com.Ambalaj.Ambalaj.dto.CompanySignupRequestDTO;
import com.Ambalaj.Ambalaj.model.CompanyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {AppUserMapper.class, CityMapper.class, CategoryMapper.class, IndustryMapper.class})
public interface CompanyMapper {
    CompanyDTO toDto(CompanyEntity company);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "appUser", source = "user")
    @Mapping(target = "appUser.type", expression = "java(com.Ambalaj.Ambalaj.enums.AppUserType.COMPANY)")
    CompanyEntity toEntityFromSignup(CompanySignupRequestDTO companySignupRequestDTO);
}
