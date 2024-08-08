package com.Ambalaj.Ambalaj.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ForgotPasswordRequestDTO {
    @Email(message = "Please enter a valid email.")
    @NotBlank(message = "Email is required.")
    private String email;
}
