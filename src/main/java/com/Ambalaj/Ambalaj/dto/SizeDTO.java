package com.Ambalaj.Ambalaj.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SizeDTO {
    private Integer id;

    @NotBlank(message = "Size name is required")
    @Size(max = 15, message = "Size name should be less than 15 characters")
    private String name;
}
