package com.Ambalaj.Ambalaj.useCase.impl;

import com.Ambalaj.Ambalaj.dto.ProductRequestDTO;
import com.Ambalaj.Ambalaj.mapper.ProductMapper;
import com.Ambalaj.Ambalaj.model.ProductEntity;
import com.Ambalaj.Ambalaj.model.ProductImageEntity;
import com.Ambalaj.Ambalaj.service.ProductService;
import com.Ambalaj.Ambalaj.useCase.ProductUseCase;
import com.Ambalaj.Ambalaj.utils.FileUtil;
import com.Ambalaj.Ambalaj.utils.FilesFolders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class ProductUseCaseImpl implements ProductUseCase {
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final FileUtil fileUtil;

    @Override
    public void addProduct(ProductRequestDTO productRequestDTO) {
        List<String> images = fileUtil.saveFiles(productRequestDTO.getImages(), FilesFolders.PRODUCTS_FOLDER);
        try {
            ProductEntity productEntity = productMapper.toEntityFromProductRequestDTO(productRequestDTO);
            // Create Image Entities with Main Image Flag
            List<ProductImageEntity> imagesEntities = IntStream.range(0, images.size()).mapToObj(idx -> {
                ProductImageEntity productImageEntity = new ProductImageEntity();
                productImageEntity.setImage(images.get(idx));
                productImageEntity.setIsMain(idx == productRequestDTO.getMainImageIdx());
                productImageEntity.setProduct(productEntity);
                return productImageEntity;
            }).collect(Collectors.toList());
            productEntity.setImages(imagesEntities);
            productService.addProduct(productEntity);
        } catch (Exception ex) {
            fileUtil.deleteFilesAsync(images);
            throw ex;
        }
    }
}
