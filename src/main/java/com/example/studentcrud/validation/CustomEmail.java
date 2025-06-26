package com.example.studentcrud.validation;

import jakarta.validation.*;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CustomEmailValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomEmail {
    String message() default "Email must be from allowed domains";
    String[] allowedDomains();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
