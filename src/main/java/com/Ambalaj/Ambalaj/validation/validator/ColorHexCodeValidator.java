package com.Ambalaj.Ambalaj.validation.validator;

import com.Ambalaj.Ambalaj.utils.Regex;
import com.Ambalaj.Ambalaj.validation.annotation.ColorHexCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ColorHexCodeValidator implements ConstraintValidator<ColorHexCode, String> {
    @Override
    public boolean isValid(String colorHexCode, ConstraintValidatorContext context) {
        if (colorHexCode == null || colorHexCode.isEmpty()) {
            return false;
        }
        return colorHexCode.matches(Regex.COLOR_HEX_CODE_PATTERN);
    }
}
