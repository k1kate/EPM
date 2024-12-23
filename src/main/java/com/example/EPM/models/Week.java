package com.example.EPM.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "week")
@Data
public class Week {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Единый автоинкрементный идентификатор

    @Column(name = "day")
    private String day;

    @OneToMany(mappedBy = "week", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GroupDiscipline> groupDiscipline;

    @ManyToOne
    @JoinColumn(name = "timetable_id")
    private TimeTable timeTable;
}
