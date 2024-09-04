package com.Ambalaj.Ambalaj.dto;

import com.Ambalaj.Ambalaj.enums.MaterialSpecificationType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MaterialSpecificationNameDTO {
    private Long id;

    @NotBlank(message = "Material specification name is required.")
    private String name;

    private MaterialSpecificationType type;
}
