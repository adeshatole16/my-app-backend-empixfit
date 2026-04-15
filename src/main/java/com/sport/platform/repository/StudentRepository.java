package com.sport.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sport.platform.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	Student findByUsername(String username);
}