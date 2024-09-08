package com.Ambalaj.Ambalaj.mapper;

import com.Ambalaj.Ambalaj.dto.ProductDTO;
import com.Ambalaj.Ambalaj.dto.PaginatedDTO;
import com.Ambalaj.Ambalaj.dto.ProductRequestDTO;
import com.Ambalaj.Ambalaj.model.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toDto(ProductEntity productEntity);

    ProductEntity toEntity(ProductDTO productDTO);

    @Mapping(target = "images", ignore = true)
    ProductEntity toEntityFromProductRequestDTO(ProductRequestDTO productRequestDTO);

    @Mapping(target = "data", source = "content")
    @Mapping(target = "currentPage", source = "number")
    @Mapping(target = "pageSize", source = "size")
    PaginatedDTO<ProductDTO> toPaginatedDto(Page<ProductEntity> page);
}
