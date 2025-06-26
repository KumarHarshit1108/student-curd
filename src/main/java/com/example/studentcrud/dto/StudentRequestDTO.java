
package com.example.studentcrud.dto;

import com.example.studentcrud.validation.Adult;
import com.example.studentcrud.validation.CustomEmail;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequestDTO {

    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[A-Za-z ]{3,}$", message = "Name must be at least 3 letters and only alphabets")
    private String name;

    @NotBlank(message = "Email is required")
    @Pattern(regexp = "^[\\w.-]+@[\\w.-]+\\.com$", message = "Email must be a valid .com address")
    @CustomEmail(allowedDomains = {"gmail.com", "example.com"})
    private String email;

    @Adult(18)
    private int age;
}
