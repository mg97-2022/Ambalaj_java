package com.Ambalaj.Ambalaj.mapper;

import com.Ambalaj.Ambalaj.dto.CompanyDTO;
import com.Ambalaj.Ambalaj.dto.CompanySignupRequestDTO;
import com.Ambalaj.Ambalaj.dto.PaginatedDTO;
import com.Ambalaj.Ambalaj.model.CompanyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring",
        uses = {AppUserMapper.class, CityMapper.class, CategoryMapper.class, IndustryMapper.class})
public interface CompanyMapper {
    @Named("defaultDto")
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "phoneNumbers", ignore = true)
    @Mapping(target = "website", ignore = true)
    @Mapping(target = "taxNumber", ignore = true)
    @Mapping(target = "city", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "industries", ignore = true)
    CompanyDTO toDto(CompanyEntity company);

    @Named("lessDetailsDto")
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "phoneNumbers", ignore = true)
    @Mapping(target = "website", ignore = true)
    @Mapping(target = "taxNumber", ignore = true)
    @Mapping(target = "city", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "industries", ignore = true)
    CompanyDTO toLessDetailsDTO(CompanyEntity company);

    @Named("allDetailsDto")
    @Mapping(target = "email", source = "appUser.email")
    CompanyDTO toAllDetailsDto(CompanyEntity company);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "appUser", source = "user")
    @Mapping(target = "appUser.type", expression = "java(com.Ambalaj.Ambalaj.enums.AppUserType.COMPANY)")
    CompanyEntity toEntityFromSignup(CompanySignupRequestDTO companySignupRequestDTO);

    @Mapping(target = "data", source = "content", qualifiedByName = "lessDetailsDto")
    @Mapping(target = "currentPage", source = "number")
    @Mapping(target = "pageSize", source = "size")
    PaginatedDTO<CompanyDTO> toPaginatedDto(Page<CompanyEntity> page);
}
