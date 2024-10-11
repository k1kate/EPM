package com.example.EPM.repository;

import com.example.EPM.models.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialtyRepository extends JpaRepository<Specialty, Integer> {
    boolean existsBynameSpecialty(String nameSpecialty);
}
