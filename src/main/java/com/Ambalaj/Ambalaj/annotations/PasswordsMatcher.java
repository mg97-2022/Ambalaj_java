package com.Ambalaj.Ambalaj.annotations;

import com.Ambalaj.Ambalaj.validation.PasswordsMatcherValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordsMatcherValidator.class)
public @interface PasswordsMatcher {
    String message() default "Passwords doesn't match.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
