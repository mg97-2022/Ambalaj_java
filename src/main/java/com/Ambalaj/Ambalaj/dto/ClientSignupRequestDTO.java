package com.Ambalaj.Ambalaj.dto;

import com.Ambalaj.Ambalaj.validation.annotation.ValidPhoneNumber;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClientSignupRequestDTO {
    @Valid
    @NotNull(message = "User Object is required.")
    private SignupAppUserDTO user;

    @NotBlank(message = "Client first name is required.")
    private String firstName;

    @NotBlank(message = "Client last name is required.")
    private String lastName;

    @ValidPhoneNumber
    private String phoneNumber;
}
