package com.Ambalaj.Ambalaj.validation;

import com.Ambalaj.Ambalaj.annotations.PasswordsMatcher;
import com.Ambalaj.Ambalaj.dto.SignupRequestDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordsMatcherValidator implements ConstraintValidator<PasswordsMatcher, SignupRequestDTO> {
    @Override
    public boolean isValid(SignupRequestDTO signupRequestDTO, ConstraintValidatorContext context) {
        return signupRequestDTO.getPassword().equals(signupRequestDTO.getPasswordConfirmation());
    }
}
