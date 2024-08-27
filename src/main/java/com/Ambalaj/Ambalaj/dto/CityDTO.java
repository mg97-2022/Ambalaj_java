package com.Ambalaj.Ambalaj.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CityDTO {
    private Long id;

    @NotBlank(message = "City name is required.")
    @Size(min = 2, max = 100, message = "City name should be between 2 and 100 characters")
    private String name;

    @Valid
    @NotNull(message = "Country is required.")
    private CountryDTO country;
}
