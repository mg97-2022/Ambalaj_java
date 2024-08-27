package com.Ambalaj.Ambalaj.validation.annotation;

import com.Ambalaj.Ambalaj.validation.validator.ColorHexCodeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ColorHexCodeValidator.class)
public @interface ColorHexCode {
    String message() default "Invalid color hex code.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
