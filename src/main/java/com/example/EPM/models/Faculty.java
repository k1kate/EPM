package com.example.EPM.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "faculty")
@Data
public class Faculty {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name_faculty")
    private String nameFaculty;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Specialty> specialties;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students;

    @Override
    public String toString() {
        return "Faculty{id=" + id + ", nameFaculty='" + nameFaculty + "'}";
    }

}
