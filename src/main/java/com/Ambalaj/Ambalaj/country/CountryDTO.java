package com.Ambalaj.Ambalaj.country;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CountryDTO {
    @NotBlank(message = "Country name is required.")
    @Size(min = 2, max = 100, message = "Country name must be between 2 and 100 characters.")
    private String name;
}
