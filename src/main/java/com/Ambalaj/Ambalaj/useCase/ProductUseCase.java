package com.Ambalaj.Ambalaj.useCase;

import com.Ambalaj.Ambalaj.dto.PaginatedDTO;
import com.Ambalaj.Ambalaj.dto.ProductDTO;
import com.Ambalaj.Ambalaj.dto.ProductRequestDTO;

import java.util.List;

public interface ProductUseCase {
    void addProduct(ProductRequestDTO productRequestDTO);

    PaginatedDTO<ProductDTO> getPaginatedProducts(
            Integer page, Integer pageSize, String search, List<Long> industries, List<Long> materials,
            List<Integer> sizes, List<Integer> colors, Long category);

    ProductDTO getProduct(Long productId);

    PaginatedDTO<ProductDTO> getPaginatedProductSimilarProducts(Integer page, Integer pageSize, Long productId);
}
