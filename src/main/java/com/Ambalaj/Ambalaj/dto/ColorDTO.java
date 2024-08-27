package com.Ambalaj.Ambalaj.dto;

import com.Ambalaj.Ambalaj.validation.annotation.ColorHexCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ColorDTO {
    private Integer id;

    @NotBlank(message = "Color name is required.")
    @Size(min = 2, max = 50, message = "Color name should be between 2 and 50 characters")
    private String name;

    @ColorHexCode
    private String hexCode;
}
