package com.Ambalaj.Ambalaj.validation;

import com.Ambalaj.Ambalaj.annotations.ValidPhoneNumber;
import com.Ambalaj.Ambalaj.utils.Regex;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {
    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false;
        }
        return phoneNumber.matches(Regex.PHONE_NUMBER_PATTERN);
    }
}
