package com.Ambalaj.Ambalaj.controller;

import com.Ambalaj.Ambalaj.dto.ProductDTO;
import com.Ambalaj.Ambalaj.dto.PaginatedDTO;
import com.Ambalaj.Ambalaj.dto.ProductRequestDTO;
import com.Ambalaj.Ambalaj.dto.ResponseDTO;
import com.Ambalaj.Ambalaj.useCase.ProductUseCase;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductUseCase productUseCase;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Null> addProduct(
            @Validated @ModelAttribute ProductRequestDTO productRequestDTO) throws IOException {
        productUseCase.addProduct(productRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<PaginatedDTO<ProductDTO>>> getProducts(
            @RequestParam @Min(0) Integer page,
            @RequestParam(defaultValue = "10", required = false) @Min(1) @Max(100) Integer pageSize,
            @RequestParam(required = false) String search, @RequestParam(required = false) List<Long> industries,
            @RequestParam(required = false) List<Long> materials, @RequestParam(required = false) List<Integer> sizes,
            @RequestParam(required = false) List<Integer> colors, @RequestParam(required = false) Long category) {
        PaginatedDTO<ProductDTO> products =
                productUseCase.getPaginatedProducts(page, pageSize, search, industries, materials, sizes, colors,
                                                    category);
        return ResponseEntity.ok(ResponseDTO.<PaginatedDTO<ProductDTO>>builder().data(products).build());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ResponseDTO<ProductDTO>> getProduct(@PathVariable Long productId) {
        ProductDTO product = productUseCase.getProduct(productId);
        return ResponseEntity.ok(ResponseDTO.<ProductDTO>builder().data(product).build());
    }

    @GetMapping("/{productId}/similar")
    public ResponseEntity<ResponseDTO<PaginatedDTO<ProductDTO>>> getProduct(
            @RequestParam @Min(0) Integer page,
            @RequestParam(defaultValue = "10", required = false) @Min(1) @Max(100) Integer pageSize,
            @PathVariable Long productId) {
        PaginatedDTO<ProductDTO> product = productUseCase.getPaginatedProductSimilarProducts(page, pageSize, productId);
        return ResponseEntity.ok(ResponseDTO.<PaginatedDTO<ProductDTO>>builder().data(product).build());
    }
}
