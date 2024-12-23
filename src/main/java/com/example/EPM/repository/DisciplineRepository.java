package com.example.EPM.repository;

import com.example.EPM.models.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplineRepository extends JpaRepository<Discipline, Integer> {
}
