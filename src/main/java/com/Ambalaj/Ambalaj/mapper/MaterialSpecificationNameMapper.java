package com.Ambalaj.Ambalaj.mapper;

import com.Ambalaj.Ambalaj.dto.MaterialSpecificationNameDTO;
import com.Ambalaj.Ambalaj.enums.MaterialSpecificationType;
import com.Ambalaj.Ambalaj.model.MaterialSpecificationNameEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MaterialSpecificationNameMapper {
    MaterialSpecificationNameDTO toDto(MaterialSpecificationNameEntity materialSpecificationEntity);

    @Mapping(target = "type", source = "type",
            defaultExpression = "java(com.Ambalaj.Ambalaj.enums.MaterialSpecificationType.TEXT)")
    MaterialSpecificationNameEntity toEntity(MaterialSpecificationNameDTO material);

    List<MaterialSpecificationNameDTO> toListDto(List<MaterialSpecificationNameEntity> materialSpecificationEntities);

    default MaterialSpecificationType mapType(MaterialSpecificationType type) {
        return type != null ? type : MaterialSpecificationType.TEXT;
    }
}
