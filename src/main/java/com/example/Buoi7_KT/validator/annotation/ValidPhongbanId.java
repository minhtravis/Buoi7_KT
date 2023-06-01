package com.example.Buoi7_KT.validator.annotation;

import com.example.Buoi7_KT.validator.ValidPhongbanIdValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidPhongbanIdValidator.class)
@Documented
public @interface ValidPhongbanId {
    String message() default "Invalid Phongban ID";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};

}
