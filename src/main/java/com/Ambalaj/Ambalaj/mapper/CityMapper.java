package com.Ambalaj.Ambalaj.mapper;

import com.Ambalaj.Ambalaj.dto.CityDTO;
import com.Ambalaj.Ambalaj.model.CityEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {
    CityDTO toDto(CityEntity cityEntity);

    CityEntity toEntity(CityDTO cityDTO);

    List<CityDTO> toListDto(List<CityEntity> cityEntities);
}
