package com.Ambalaj.Ambalaj.validation;

import com.Ambalaj.Ambalaj.annotations.PasswordsMatcher;
import com.Ambalaj.Ambalaj.dto.SignupAppUserDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordsMatcherValidator implements ConstraintValidator<PasswordsMatcher, SignupAppUserDTO> {
    @Override
    public boolean isValid(SignupAppUserDTO signupRequestDTO, ConstraintValidatorContext context) {
        if (signupRequestDTO.getPassword() == null || signupRequestDTO.getPasswordConfirmation() == null) return false;
        return signupRequestDTO.getPassword().equals(signupRequestDTO.getPasswordConfirmation());
    }
}
