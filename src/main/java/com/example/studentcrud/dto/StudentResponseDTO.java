package com.example.studentcrud.dto;

public record StudentResponseDTO(
    Long id, String name, String email, int age
) {}
