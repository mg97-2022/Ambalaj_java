package com.Ambalaj.Ambalaj.dto;

import com.Ambalaj.Ambalaj.validation.annotation.ValidPhoneNumber;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClientSignupClientDTO {
    @NotBlank(message = "Client first name is required.")
    private String firstName;

    @NotBlank(message = "Client last name is required.")
    private String lastName;

    @ValidPhoneNumber
    private String phoneNumber;
}
