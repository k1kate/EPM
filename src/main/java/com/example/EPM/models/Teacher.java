package com.example.EPM.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "teacher")
@Data
public class Teacher {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "surname_student")
    private String surnameTeacher;

    @Column(name = "name_student")
    private String nameTeacher;

    @Column(name = "patronymic_student")
    private String patronymicTeacher;

    @Column(name = "fullname")
    private String fullName;

    @PrePersist
    @PreUpdate
    private void generateFullName() {
        String initialName = nameTeacher != null && !nameTeacher.isEmpty() ? nameTeacher.substring(0, 1) + "." : "";
        String initialPatronymic = patronymicTeacher != null && !patronymicTeacher.isEmpty() ? patronymicTeacher.substring(0, 1) + "." : "";
        this.fullName = (surnameTeacher != null ? surnameTeacher : "") + "_" + initialName + initialPatronymic;
    }

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "groupTimeTable_id", referencedColumnName = "id")
    private GroupTimeTable groupTimeTable;
}
