package com.Ambalaj.Ambalaj.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class IndustryDTO {
    private Long id;

    @NotBlank(message = "Industry name is required.")
    @Size(min = 2, max = 100, message = "Industry name should be between 2 and 100 characters")
    private String name;
}
