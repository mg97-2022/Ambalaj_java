package com.Ambalaj.Ambalaj.mapper;

import com.Ambalaj.Ambalaj.dto.MaterialDTO;
import com.Ambalaj.Ambalaj.dto.MaterialRequestDTO;
import com.Ambalaj.Ambalaj.dto.PaginatedDTO;
import com.Ambalaj.Ambalaj.model.MaterialEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", uses = MaterialSpecificationNameMapper.class)
public interface MaterialMapper {
    @Named("toDto")
    MaterialDTO toDto(MaterialEntity materialEntity);

    @Named("toNameAndIdDto")
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "specifications", ignore = true)
    MaterialDTO toNameAndIdDto(MaterialEntity materialEntity);

    MaterialEntity toEntity(MaterialDTO material);

    @Mapping(target = "image", ignore = true)
    MaterialEntity toEntityFromMaterialRequestDTO(MaterialRequestDTO materialRequestDTO);

    @IterableMapping(qualifiedByName = "toNameAndIdDto")
    List<MaterialDTO> toListDto(List<MaterialEntity> materialEntities);

    @Mapping(target = "data", source = "content", qualifiedByName = "toDto")
    @Mapping(target = "currentPage", source = "number")
    @Mapping(target = "pageSize", source = "size")
    PaginatedDTO<MaterialDTO> toPaginatedDto(Page<MaterialEntity> page);
}
