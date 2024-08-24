package com.Ambalaj.Ambalaj.mapper;

import com.Ambalaj.Ambalaj.dto.CategoryDTO;
import com.Ambalaj.Ambalaj.model.CategoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toDto(CategoryEntity categoryEntity);

    CategoryEntity toEntity(CategoryDTO categoryDTO);

    List<CategoryDTO> toListDto(List<CategoryEntity> categoryEntities);
}
