package com.Ambalaj.Ambalaj.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
// Used to ignore cities when it's in request body but consider it in response body
@JsonIgnoreProperties(value = {"cities"}, allowGetters = true)
public class CountryDTO {
    private Integer id;

    @NotBlank(message = "Country name is required.")
    @Size(min = 2, max = 100, message = "Country name should be between 2 and 100 characters")
    private String name;

    private List<CityDTOWithoutCountry> cities;
}
