package com.Ambalaj.Ambalaj.dto;

import lombok.Data;

@Data
public class CategoryDTO {
    private Long id;
    private String name;
    private String image;
    private CategoryDTO parentCategory;
}
