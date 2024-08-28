package com.Ambalaj.Ambalaj.mapper;

import com.Ambalaj.Ambalaj.dto.SizeDTO;
import com.Ambalaj.Ambalaj.model.SizeEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SizeMapper {
    SizeDTO toDto(SizeEntity sizeEntity);

    SizeEntity toEntity(SizeDTO sizeDTO);

    List<SizeDTO> toListDto(List<SizeEntity> sizeEntities);
}
