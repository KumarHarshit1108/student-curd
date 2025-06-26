package com.example.studentcrud.service;

import com.example.studentcrud.dto.*;
import com.example.studentcrud.entity.Student;
import com.example.studentcrud.exception.StudentNotFoundException;
import com.example.studentcrud.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    public StudentResponseDTO create(StudentRequestDTO dto) {
        Student student = Student.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .age(dto.getAge())
                .build();
        student = repo.save(student);
        return toDto(student);
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
        return toDto(repo.save(student));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    private StudentResponseDTO toDto(Student s) {
        return new StudentResponseDTO(s.getId(), s.getName(), s.getEmail(), s.getAge());
    }
}
