package com.Ambalaj.Ambalaj.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryDTO {
    private Long id;

    @NotBlank(message = "Category name is required.")
    @Size(min = 2, max = 100, message = "Category name should be between 2 and 100 characters")
    private String name;

    @Valid
    private CategoryDTO parentCategory;
}
