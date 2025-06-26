package com.example.studentcrud.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class CustomEmailValidator implements ConstraintValidator<CustomEmail, String> {

    private String[] allowedDomains;

    @Override
    public void initialize(CustomEmail annotation) {
        this.allowedDomains = annotation.allowedDomains();
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || !email.contains("@")) return false;

        String domain = email.substring(email.indexOf("@") + 1).toLowerCase();
        return Arrays.asList(allowedDomains).contains(domain);
    }
}
