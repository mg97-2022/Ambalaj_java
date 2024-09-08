package com.Ambalaj.Ambalaj.validation.annotation;

import com.Ambalaj.Ambalaj.validation.validator.ProductMainImageIdxValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProductMainImageIdxValidator.class)
public @interface ProductMainImageIdx {
    String message() default "Product main image number must be valid.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
