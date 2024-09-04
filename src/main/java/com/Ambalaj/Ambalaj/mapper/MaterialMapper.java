package com.Ambalaj.Ambalaj.mapper;

import com.Ambalaj.Ambalaj.dto.MaterialDTO;
import com.Ambalaj.Ambalaj.dto.MaterialRequestDTO;
import com.Ambalaj.Ambalaj.dto.PaginatedDTO;
import com.Ambalaj.Ambalaj.model.MaterialEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", uses = MaterialSpecificationNameMapper.class)
public interface MaterialMapper {
    MaterialDTO toDto(MaterialEntity materialEntity);

    MaterialEntity toEntity(MaterialDTO material);

    @Mapping(target = "image", ignore = true)
    MaterialEntity toEntityFromMaterialRequestDTO(MaterialRequestDTO materialRequestDTO);

    @Mapping(target = "data", source = "content")
    @Mapping(target = "currentPage", source = "number")
    @Mapping(target = "pageSize", source = "size")
    PaginatedDTO<MaterialDTO> toPaginatedDto(Page<MaterialEntity> page);
}
