package com.Ambalaj.Ambalaj.dto;

import lombok.Data;

import java.util.List;

@Data
public class MaterialDTO {
    private Long id;
    private String name;
    private String description;
    private String image;
    private List<MaterialSpecificationDTO> specifications;
}
