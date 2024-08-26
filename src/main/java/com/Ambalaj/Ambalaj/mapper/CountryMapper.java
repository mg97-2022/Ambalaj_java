package com.Ambalaj.Ambalaj.mapper;

import com.Ambalaj.Ambalaj.dto.CountryDTO;
import com.Ambalaj.Ambalaj.dto.PaginatedDTO;
import com.Ambalaj.Ambalaj.model.CountryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    CountryDTO toDto(CountryEntity countryEntity);

    CountryEntity toEntity(CountryDTO countryDTO);

    List<CountryDTO> toListDto(List<CountryEntity> countryEntities);

    @Mapping(target = "data", source = "content")
    @Mapping(target = "currentPage", source = "number")
    @Mapping(target = "pageSize", source = "size")
    @Mapping(target = "totalPages", expression = "java((long) page.getTotalPages())")
    @Mapping(target = "totalNumber", source = "totalElements")
    PaginatedDTO<CountryDTO> toPaginatedDTO(Page<CountryEntity> page);
}
