package com.example.studentcrud.validation;

import jakarta.validation.*;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AdultValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Adult {
    String message() default "Age must be at least {value}";
    int value() default 18;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
