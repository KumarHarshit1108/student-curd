package com.example.studentcrud.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= GenderValidator.class)
public @interface Gender {
	String message() default "Gender must be from allowed genderList";
    String[] genderList();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
