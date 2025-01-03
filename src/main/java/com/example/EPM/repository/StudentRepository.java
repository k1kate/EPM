package com.example.EPM.repository;

import com.example.EPM.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByUser_Id(Long user_id);
}