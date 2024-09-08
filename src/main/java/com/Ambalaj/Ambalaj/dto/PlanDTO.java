package com.Ambalaj.Ambalaj.dto;

import com.Ambalaj.Ambalaj.enums.PlanPriorityNumber;
import com.Ambalaj.Ambalaj.enums.PlanStatus;
import com.Ambalaj.Ambalaj.enums.WebsiteUserType;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PlanDTO {
    private Integer id;

    @NotBlank(message = "Plan name is required")
    @Size(min = 2, max = 100, message = "Plan name should be between 2 and 100 characters")
    private String name;

    @NotNull(message = "Price per month is required")
    @DecimalMin(value = "0.00", inclusive = true, message = "Price per month must be positive")
    @Digits(integer = 8, fraction = 2, message = "Price per month must be within 8 integer and 2 fraction digits")
    private BigDecimal pricePerMonth;

    @NotNull(message = "Price per year is required")
    @DecimalMin(value = "0.00", inclusive = true, message = "Price per year must be positive")
    @Digits(integer = 8, fraction = 2, message = "Price per year must be within 8 integer and 2 fraction digits")
    private BigDecimal pricePerYear;

    @DecimalMin(value = "0.00", inclusive = true, message = "Discount must be positive")
    @Digits(integer = 3, fraction = 2, message = "Discount must be within 3 integer and 2 fraction digits")
    private BigDecimal discount;

    private Boolean isPopular;

    @NotNull(message = "Account type is required")
    private WebsiteUserType accountType;

    // Used with company account type
    // Could be validated with annotation on the class level
    // to check for account type is company
    @Positive(message = "Products number to create must be positive")
    private Integer productsNumberToCreate;

    private PlanPriorityNumber priorityNumber;

    private PlanStatus status;
}
