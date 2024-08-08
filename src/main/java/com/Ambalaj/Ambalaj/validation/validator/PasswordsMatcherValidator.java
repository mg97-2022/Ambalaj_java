package com.Ambalaj.Ambalaj.validation.validator;

import com.Ambalaj.Ambalaj.validation.annotation.PasswordsMatcher;
import com.Ambalaj.Ambalaj.validation.interfaces.PasswordConfirmable;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordsMatcherValidator implements ConstraintValidator<PasswordsMatcher, PasswordConfirmable> {
    @Override
    public boolean isValid(PasswordConfirmable signupRequestDTO, ConstraintValidatorContext context) {
        if (signupRequestDTO.getPassword() == null || signupRequestDTO.getPasswordConfirmation() == null) return false;
        return signupRequestDTO.getPassword().equals(signupRequestDTO.getPasswordConfirmation());
    }
}
