package com.Ambalaj.Ambalaj.mapper;

import com.Ambalaj.Ambalaj.dto.CountryDTO;
import com.Ambalaj.Ambalaj.model.CountryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    CountryDTO toDto(CountryEntity countryEntity);

    CountryEntity toEntity(CountryDTO countryDTO);

    List<CountryDTO> toListDto(List<CountryEntity> countryEntities);
}
