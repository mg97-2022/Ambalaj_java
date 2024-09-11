package com.Ambalaj.Ambalaj.service;

import com.Ambalaj.Ambalaj.model.ProductEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    void addProduct(ProductEntity productEntity);

    Page<ProductEntity> getPaginatedProducts(
            Integer page, Integer pageSize, String search, List<Long> industries, List<Long> materials,
            List<Integer> sizes, List<Integer> colors, Long category);

    ProductEntity getProduct(Long productId);

    Page<ProductEntity> getPaginatedProductSimilarProducts(Integer page, Integer pageSize, Long productId);
}
