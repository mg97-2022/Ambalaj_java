package com.Ambalaj.Ambalaj.useCase.impl;

import com.Ambalaj.Ambalaj.dto.PaginatedDTO;
import com.Ambalaj.Ambalaj.dto.ProductDTO;
import com.Ambalaj.Ambalaj.dto.ProductRequestDTO;
import com.Ambalaj.Ambalaj.mapper.ProductMapper;
import com.Ambalaj.Ambalaj.model.ProductEntity;
import com.Ambalaj.Ambalaj.model.ProductImageEntity;
import com.Ambalaj.Ambalaj.service.ProductService;
import com.Ambalaj.Ambalaj.useCase.ProductUseCase;
import com.Ambalaj.Ambalaj.utils.FileUtil;
import com.Ambalaj.Ambalaj.utils.FilesFolders;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    @Override
    public PaginatedDTO<ProductDTO> getPaginatedProducts(
            Integer page, Integer pageSize, String search, List<Long> industries, List<Long> materials,
            List<Integer> sizes, List<Integer> colors, Long category) {
        Page<ProductEntity> pageableProductsList =
                productService.getPaginatedProducts(page, pageSize, search, industries, materials, sizes, colors,
                                                    category);
        return productMapper.toPaginatedDto(pageableProductsList);
    }

    @Override
    public ProductDTO getProduct(Long productId) {
        ProductEntity product = productService.getProduct(productId);
        return productMapper.toDto(product);
    }

    @Override
    public PaginatedDTO<ProductDTO> getPaginatedProductSimilarProducts(
            Integer page, Integer pageSize, Long productId) {
        Page<ProductEntity> pageableProductsList =
                productService.getPaginatedProductSimilarProducts(page, pageSize, productId);
        return productMapper.toPaginatedDto(pageableProductsList);
    }
}
