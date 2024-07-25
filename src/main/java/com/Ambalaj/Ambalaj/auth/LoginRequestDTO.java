package com.Ambalaj.Ambalaj.auth;

import com.Ambalaj.Ambalaj.utils.AppConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {
    @Email(message = "Please enter a valid email.")
    @NotBlank(message = "Email is required.")
    private String email;
    @NotNull(message = "Please enter your password.")
    @Pattern(regexp = AppConstants.PASSWORD_PATTERN, message = AppConstants.PASSWORD_ERROR_MESSAGE)
    private String password;
}
