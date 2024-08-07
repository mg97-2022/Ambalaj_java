package com.Ambalaj.Ambalaj.annotations;

import com.Ambalaj.Ambalaj.validation.PhoneNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE_USE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface ValidPhoneNumber {
    String message() default "Invalid phone number.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}


