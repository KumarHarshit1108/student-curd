package com.example.studentcrud.service;

import com.example.studentcrud.dto.*;
import com.example.studentcrud.entity.Student;
import com.example.studentcrud.exception.StudentExistsException;
import com.example.studentcrud.exception.StudentNotFoundException;
import com.example.studentcrud.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    public StudentResponseDTO create(StudentRequestDTO dto) {
    	Optional<Student> stud = repo.findByEmail(dto.getEmail());
    	if(stud.isPresent()) {
    		throw new StudentExistsException(dto.getEmail());
    	}
    	else {
        Student student = Student.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .age(dto.getAge())
                .gender(dto.getGender())
                .build();
        student = repo.save(student);
        return toDto(student);
    	}
    }

    public StudentResponseDTO getById(Long id) {
        return repo.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    public List<StudentResponseDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<StudentResponseDTO> getStudentsAboveAge(int age) {
        return repo.findStudentsWithMinAge(age)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public StudentResponseDTO update(Long id, StudentRequestDTO dto) {
        Student student = repo.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setAge(dto.getAge());
        student.setGender(dto.getGender());
        return toDto(repo.save(student));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
    
    public List<StudentResponseDTO> getMaleStudents() {
    	List<Student> maleStudents = repo.findByGender();
    	return maleStudents.stream()
    			.map(this::toDto)
    			.collect(Collectors.toList());
    }

    private StudentResponseDTO toDto(Student s) {
        return new StudentResponseDTO(s.getId(), s.getName(), s.getEmail(), s.getAge(), s.getGender());
    }
}
