package com.example.EPM.repository;

import com.example.EPM.models.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
    boolean existsBynameFaculty(String namefaculty);
//    Faculty findByKodSpec(Integer kodSpec);
}
