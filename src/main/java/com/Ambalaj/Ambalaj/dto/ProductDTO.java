package com.Ambalaj.Ambalaj.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Integer minOrder;
    private String companyName;
    private CategoryDTO category;
    private MaterialDTO material;
    private List<String> images;
    private List<SizeDTO> sizes;
    private List<ColorDTO> colors;
}
