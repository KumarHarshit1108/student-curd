package com.example.studentcrud.exception;

public class StudentExistsException extends RuntimeException {
	public StudentExistsException(String email) {
		super("Student already exists with the id "+ email);
	}
}
