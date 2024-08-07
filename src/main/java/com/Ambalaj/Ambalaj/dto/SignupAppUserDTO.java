package com.Ambalaj.Ambalaj.dto;

import com.Ambalaj.Ambalaj.annotations.PasswordsMatcher;
import com.Ambalaj.Ambalaj.utils.GeneralMessages;
import com.Ambalaj.Ambalaj.utils.Regex;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@PasswordsMatcher
public class SignupAppUserDTO {
    @Email(message = "Please enter a valid email.")
    @NotBlank(message = "Email is required.")
    private String email;

    @NotNull(message = "Please enter your password.")
    @Pattern(regexp = Regex.PASSWORD_PATTERN, message = GeneralMessages.PASSWORD_ERROR_MESSAGE)
    private String password;

    private String passwordConfirmation;
}
