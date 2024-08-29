package com.Ambalaj.Ambalaj.mapper;

import com.Ambalaj.Ambalaj.dto.CategoryDTO;
import com.Ambalaj.Ambalaj.dto.CategoryRequestDTO;
import com.Ambalaj.Ambalaj.dto.PaginatedDTO;
import com.Ambalaj.Ambalaj.model.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toDto(CategoryEntity categoryEntity);

    CategoryEntity toEntity(CategoryDTO categoryDTO);

    @Mapping(target = "image", ignore = true)
    CategoryEntity toEntityFromCategoryRequestDTO(CategoryRequestDTO categoryRequestDTO);

    List<CategoryDTO> toListDto(List<CategoryEntity> categoryEntities);

    @Mapping(target = "data", source = "content")
    @Mapping(target = "currentPage", source = "number")
    @Mapping(target = "pageSize", source = "size")
    PaginatedDTO<CategoryDTO> toPaginatedDto(Page<CategoryEntity> page);
}
