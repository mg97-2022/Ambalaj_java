package com.Ambalaj.Ambalaj.dto;

import com.Ambalaj.Ambalaj.validation.annotation.ProductMainImageIdx;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@ProductMainImageIdx
public class ProductRequestDTO {
    @NotBlank(message = "Product name is required")
    @Size(min = 2, max = 200, message = "Product name should be between 2 and 200 characters")
    private String name;

    @NotBlank(message = "Product description is required")
    private String description;

    @NotNull(message = "Product min order is required")
    private Integer minOrder;

    @NotNull(message = "Product images is required")
    @Size(min = 3, max = 10, message = "Product images should be between 3 and 10 images")
    private List<MultipartFile> images;

    // Validated using @ProductMainImageIdx
    private Integer mainImageIdx;

    @NotNull(message = "Product category is required")
    private CategoryDTO category;

    @NotNull(message = "Product material is required")
    private MaterialDTO material;

    @NotNull(message = "Product sizes is required")
    @Size(min = 1, message = "Please provide at least one size")
    private List<SizeDTO> sizes;

    @NotNull(message = "Product colors is required")
    @Size(min = 1, message = "Please provide at least one color")
    private List<ColorDTO> colors;
}
