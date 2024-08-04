package com.Ambalaj.Ambalaj.dto;

import com.Ambalaj.Ambalaj.annotations.PasswordsMatcher;
import com.Ambalaj.Ambalaj.utils.AppConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@PasswordsMatcher
public class SignupRequestDTO {
    @NotBlank(message = "First name is required.")
    private String firstName;

    @NotBlank(message = "Last name is required.")
    private String lastName;

    @Email(message = "Please enter a valid email.")
    @NotBlank(message = "Email is required.")
    private String email;

    @NotNull(message = "Password is required.")
    @Pattern(regexp = AppConstants.PASSWORD_PATTERN, message = AppConstants.PASSWORD_ERROR_MESSAGE)
    private String password;

    private String passwordConfirmation;
}
