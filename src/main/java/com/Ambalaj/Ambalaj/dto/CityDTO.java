package com.Ambalaj.Ambalaj.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CityDTO {
    private Long id;

    @NotBlank(message = "City name is required.")
    private String name;

    @Valid
    @NotNull(message = "Country is required.")
    private CountryDTO country;
}
