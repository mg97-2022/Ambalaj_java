package com.Ambalaj.Ambalaj.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CityDTO {
    private Long id;

    @NotBlank(message = "City name is required.")
    private String name;
}
