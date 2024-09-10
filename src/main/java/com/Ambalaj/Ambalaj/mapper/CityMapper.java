package com.Ambalaj.Ambalaj.mapper;

import com.Ambalaj.Ambalaj.dto.CityDTO;
import com.Ambalaj.Ambalaj.dto.CityDTOWithoutCountry;
import com.Ambalaj.Ambalaj.dto.PaginatedDTO;
import com.Ambalaj.Ambalaj.model.CityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", uses = CountryMapper.class)
public interface CityMapper {
    CityDTO toDto(CityEntity cityEntity);

    CityDTOWithoutCountry toDtoWithoutCountry(CityEntity cityEntity);

    List<CityDTOWithoutCountry> toListDtoWithoutCountry(List<CityEntity> cityEntities);

    CityEntity toEntity(CityDTO cityDTO);

    List<CityDTO> toListDto(List<CityEntity> cityEntities);

    @Mapping(target = "data", source = "content")
    @Mapping(target = "currentPage", source = "number")
    @Mapping(target = "pageSize", source = "size")
    PaginatedDTO<CityDTO> toPaginatedDto(Page<CityEntity> page);
}
