package com.example.EPM.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "courses")
@Data
public class Course {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "course_number")
    private String courseNumber;

    @ManyToOne
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;
}
