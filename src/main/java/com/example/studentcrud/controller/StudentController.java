
package com.example.studentcrud.controller;

import com.example.studentcrud.dto.StudentRequestDTO;
import com.example.studentcrud.dto.StudentResponseDTO;
import com.example.studentcrud.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(@Valid @RequestBody StudentRequestDTO dto) {
        return ResponseEntity.ok(service.create(dto));  
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<StudentResponseDTO>> getAllStudentBy() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/above/{age}")
    public ResponseEntity<List<StudentResponseDTO>> getStudentsAboveAge(@PathVariable int age) {
        return ResponseEntity.ok(service.getStudentsAboveAge(age));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Student deleted successfully");
    }
    @GetMapping("/male") 
    public ResponseEntity<List<StudentResponseDTO>> getMaleStudents() {
    	return ResponseEntity.ok(service.getMaleStudents());
    }
    @GetMapping("/female") 
    public ResponseEntity<List<StudentResponseDTO>> getFemaleStudents() {
    	return ResponseEntity.ok(service.getFemaleStudents());
    }
    
    @GetMapping("/ext/cats") 
    public ResponseEntity<String> getCatsFact() {
    	String fact = service.getCatFacts();
    	return ResponseEntity.ok(fact);    
    }
}
