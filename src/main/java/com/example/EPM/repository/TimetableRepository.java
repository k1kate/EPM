package com.example.EPM.repository;

import com.example.EPM.models.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimetableRepository extends JpaRepository<TimeTable, Long> {
}
