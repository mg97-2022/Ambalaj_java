package com.Ambalaj.Ambalaj.validation.validator;

import com.Ambalaj.Ambalaj.dto.ProductRequestDTO;
import com.Ambalaj.Ambalaj.validation.annotation.ProductMainImageIdx;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ProductMainImageIdxValidator implements ConstraintValidator<ProductMainImageIdx, ProductRequestDTO> {
    @Override
    public boolean isValid(ProductRequestDTO productRequestDTO, ConstraintValidatorContext context) {
        int imagesCount = productRequestDTO.getImages() != null ? productRequestDTO.getImages().size() : 0;
        Integer mainImageIdx = productRequestDTO.getMainImageIdx();
        return mainImageIdx != null && mainImageIdx >= 0 && mainImageIdx < imagesCount;
    }
}