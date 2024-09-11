package com.Ambalaj.Ambalaj.mapper;

import com.Ambalaj.Ambalaj.dto.ProductDTO;
import com.Ambalaj.Ambalaj.dto.PaginatedDTO;
import com.Ambalaj.Ambalaj.dto.ProductRequestDTO;
import com.Ambalaj.Ambalaj.model.ProductEntity;
import com.Ambalaj.Ambalaj.model.ProductImageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        uses = {CategoryMapper.class, MaterialMapper.class, SizeMapper.class, ColorMapper.class})
public interface ProductMapper {
    @Named("defaultDto")
    @Mapping(target = "companyName", source = "company.name")
    @Mapping(source = "images", target = "images", qualifiedByName = "mapImagesToStrings")
    ProductDTO toDto(ProductEntity productEntity);

    @Named("lessDetailsDto")
    @Mapping(target = "companyName", source = "company.name")
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "minOrder", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "material", ignore = true)
    @Mapping(target = "sizes", ignore = true)
    @Mapping(target = "images", source = "images", qualifiedByName = "mapImagesToMainImage")
    ProductDTO toLessDetailsDto(ProductEntity productEntity);

    @Mapping(target = "images", ignore = true)
    ProductEntity toEntityFromProductRequestDTO(ProductRequestDTO productRequestDTO);

    @Mapping(target = "data", source = "content", qualifiedByName = "lessDetailsDto")
    @Mapping(target = "currentPage", source = "number")
    @Mapping(target = "pageSize", source = "size")
    PaginatedDTO<ProductDTO> toPaginatedDto(Page<ProductEntity> page);

    @Named("mapImagesToStrings")
    default List<String> mapImagesToStrings(List<ProductImageEntity> images) {
        if (images == null) {
            return null;
        }
        return images.stream().sorted((i1, i2) -> Boolean.compare(i2.getIsMain(),
                                                                  i1.getIsMain()))  // Sort by isMain, true comes first
                     .map(ProductImageEntity::getImage).collect(Collectors.toList());
    }

    @Named("mapImagesToMainImage")
    default List<String> mapImagesToMainImage(List<ProductImageEntity> images) {
        if (images == null) {
            return null;
        }
        return images.stream().filter(ProductImageEntity::getIsMain).map(ProductImageEntity::getImage)
                     .collect(Collectors.toList());
    }
}
