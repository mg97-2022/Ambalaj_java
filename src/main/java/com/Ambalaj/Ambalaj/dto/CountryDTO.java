package com.Ambalaj.Ambalaj.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CountryDTO {
    private Integer id;

    @NotBlank(message = "Country name is required.")
    private String name;
}
