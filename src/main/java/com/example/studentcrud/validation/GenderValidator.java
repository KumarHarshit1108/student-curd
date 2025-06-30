package com.example.studentcrud.validation;

import java.util.Arrays;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<Gender, String>{
	private String[] genderList;
	
	@Override
    public void initialize(Gender annotation) {
        this.genderList = annotation.genderList();
    }
	
	@Override
	public boolean isValid(String gender, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		 if (gender == null) return false;

	        String gen = gender.toLowerCase();
	        return Arrays.asList(genderList).contains(gen);
	}

}
