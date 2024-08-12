package com.Ambalaj.Ambalaj.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClientSignupRequestDTO {
    @Valid
    @NotNull(message = "User Object is required.")
    private SignupAppUserDTO user;

    @Valid
    @NotNull(message = "Client Object is required.")
    private ClientSignupClientDTO client;
}
