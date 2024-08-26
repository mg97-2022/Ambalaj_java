package com.Ambalaj.Ambalaj.mapper;

import com.Ambalaj.Ambalaj.dto.IndustryDTO;
import com.Ambalaj.Ambalaj.dto.PaginatedDTO;
import com.Ambalaj.Ambalaj.model.IndustryEntity;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IndustryMapper {
    IndustryDTO toDto(IndustryEntity industryEntity);

    IndustryEntity toEntity(IndustryDTO industryDTO);

    List<IndustryDTO> toListDto(List<IndustryEntity> industryEntities);

    PaginatedDTO<IndustryDTO> toPaginatedDto(Page<IndustryEntity> page);
}
