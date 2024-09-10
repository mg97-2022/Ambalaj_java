package com.Ambalaj.Ambalaj.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MaterialDTO {
    private Long id;
    private String name;
    private String description;
    private String image;
    private List<MaterialSpecificationDTO> specifications;
}
