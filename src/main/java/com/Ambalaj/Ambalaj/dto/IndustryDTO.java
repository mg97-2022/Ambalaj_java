package com.Ambalaj.Ambalaj.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class IndustryDTO {
    private Long id;

    @NotBlank(message = "Industry name is required.")
    private String name;
}
