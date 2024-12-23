package com.example.EPM.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "discipline")
@Data
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time_pair")
    private String timePair;

    @Column(name = "aud")
    private String aud;

    @Column(name = "group_name")
    private String group_name;

    @Column(name = "disc")
    private String disc;

    @Column(name = "teacher")
    private String teacher;

    @ManyToOne
    @JoinColumn(name = "group_discipline_id")  // создаст внешний ключ на поле group_discipline_id
    private GroupDiscipline groupDiscipline;
}
