package com.example.EPM.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "specialty")
@Data
public class Specialty {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name_specialty")
    private String nameSpecialty;

    @ManyToOne
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;

    @OneToMany(mappedBy = "specialty", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students;

    @Override
    public String toString() {
        return "Specialty{id=" + id + ", nameSpecialty='" + nameSpecialty + "'}";
    }


}
