package com.Ambalaj.Ambalaj.dto;

import com.Ambalaj.Ambalaj.utils.Regex;
import com.Ambalaj.Ambalaj.validation.annotation.ValidPhoneNumber;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class CompanySignupRequestDTO {
    @Valid
    @NotNull(message = "User Object is required.")
    private SignupAppUserDTO user;

    @NotBlank(message = "Company name is required.")
    @Size(min = 2, max = 150, message = "Company name should be between 2 and 150 characters")
    private String name;

    @NotNull(message = "Phone numbers is required.")
    @Size(min = 1, message = "At least one phone number is required.")
    private List<@ValidPhoneNumber String> phoneNumbers;

    @NotBlank(message = "Company description is required.")
    private String description;

    @Pattern(regexp = Regex.URL_PATTERN, message = "Please enter a valid website url.")
    private String website;

    @NotBlank(message = "Tax number is required.")
    private String taxNumber;

    @Valid
    @NotNull(message = "City is required.")
    private CityDTO city;

    @NotBlank(message = "Address is required.")
    private String address;

    @NotNull(message = "Categories is required.")
    @Size(min = 1, message = "At least one category is required.")
    private List<@Valid CategoryDTO> categories;

    @NotNull(message = "Industries is required.")
    @Size(min = 1, message = "At least one industry is required.")
    private List<@Valid IndustryDTO> industries;
}
