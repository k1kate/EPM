package com.example.EPM.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "student")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne //многие к одному. много студентов в этой спец
    @JoinColumn(name = "specialty_id", nullable = false)
    private Specialty specialty;

    @ManyToOne
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "surname_student")
    private String surnameStudent;

    @Column(name = "name_student")
    private String nameStudent;

    @Column(name = "patronymic_student")
    private String patronymicStudent;



    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "groupTimeTable_id", referencedColumnName = "id")
    private GroupTimeTable groupTimeTable;
}