package com.Ambalaj.Ambalaj.mapper;

import com.Ambalaj.Ambalaj.dto.IndustryDTO;
import com.Ambalaj.Ambalaj.model.IndustryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IndustryMapper {
    IndustryDTO toDto(IndustryEntity industryEntity);

    IndustryEntity toEntity(IndustryDTO industryDTO);

    List<IndustryDTO> toListDto(List<IndustryEntity> industryEntities);
}