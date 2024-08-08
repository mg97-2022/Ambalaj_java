package com.Ambalaj.Ambalaj.dto;

import com.Ambalaj.Ambalaj.validation.annotation.ValidPhoneNumber;
import com.Ambalaj.Ambalaj.utils.Regex;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class CompanySignupCompanyDTO {
    @NotBlank(message = "Company name is required.")
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

    @NotNull(message = "City is required.")
    @Positive(message = "City id must be a number.")
    private Long cityId;

    @NotBlank(message = "Address is required.")
    private String address;

    @NotNull(message = "Categories is required.")
    @Size(min = 1, message = "At least one category is required.")
    private List<@Positive Long> categoriesId;

    @NotNull(message = "Industries is required.")
    @Size(min = 1, message = "At least one industry is required.")
    private List<@Positive Long> industriesId;
}
