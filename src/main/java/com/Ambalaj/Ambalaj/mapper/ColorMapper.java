package com.Ambalaj.Ambalaj.mapper;

import com.Ambalaj.Ambalaj.dto.ColorDTO;
import com.Ambalaj.Ambalaj.model.ColorEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ColorMapper {
    ColorDTO toDto(ColorEntity colorEntity);

    ColorEntity toEntity(ColorDTO colorDTO);

    List<ColorDTO> toListDto(List<ColorEntity> colorEntities);
}
