package com.Ambalaj.Ambalaj.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MaterialSpecificationDTO {
    @Valid
    private MaterialSpecificationNameDTO specification;

    @NotBlank(message = "Material specification value is required")
    private String value;
}
