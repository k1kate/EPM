package com.example.EPM.repository;

import com.example.EPM.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
        Teacher findByUser_Id(Integer user_id);
}
