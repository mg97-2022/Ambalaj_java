package com.Ambalaj.Ambalaj.controller;

import com.Ambalaj.Ambalaj.dto.ProductRequestDTO;
import com.Ambalaj.Ambalaj.useCase.ProductUseCase;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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
}
