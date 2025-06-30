package com.example.studentcrud.repository;

import com.example.studentcrud.entity.Student;


import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value = "SELECT * FROM student WHERE age >= :minAge", nativeQuery = true)
    List<Student> findStudentsWithMinAge(@Param("minAge") int minAge);

	Optional<Student> findByEmail(String email);
	
	@Query(value = "SELECT * FROM student WHERE gender = 'Male' or gender = 'male'", nativeQuery = true)
	List<Student> findByGender();
}
