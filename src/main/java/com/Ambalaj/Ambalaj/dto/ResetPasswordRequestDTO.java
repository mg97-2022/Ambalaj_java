package com.Ambalaj.Ambalaj.dto;

import com.Ambalaj.Ambalaj.validation.annotation.PasswordsMatcher;
import com.Ambalaj.Ambalaj.utils.GeneralMessages;
import com.Ambalaj.Ambalaj.utils.Regex;
import com.Ambalaj.Ambalaj.validation.interfaces.PasswordConfirmable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@PasswordsMatcher
public class ResetPasswordRequestDTO implements PasswordConfirmable {
    @NotNull(message = "Please enter your password.")
    @Pattern(regexp = Regex.PASSWORD_PATTERN, message = GeneralMessages.PASSWORD_ERROR_MESSAGE)
    private String password;

    private String passwordConfirmation;
}
