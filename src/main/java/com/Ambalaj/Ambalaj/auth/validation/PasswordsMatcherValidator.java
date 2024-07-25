package com.Ambalaj.Ambalaj.auth.validation;

import com.Ambalaj.Ambalaj.auth.SignupRequestDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordsMatcherValidator implements ConstraintValidator<PasswordsMatcher, SignupRequestDTO> {
    @Override
    public boolean isValid(SignupRequestDTO signupRequestDTO, ConstraintValidatorContext context) {
        return signupRequestDTO.getPassword().equals(signupRequestDTO.getPasswordConfirmation());
    }
}
