package com.example.EPM.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "timetable")
@Data
public class TimeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "timeTable", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Week> weeks;

    @ManyToOne
    @JoinColumn(name = "grouptimetable_id")
    private GroupTimeTable groupTimeTable;
    @Override
    public String toString() {
        return "TimeTable{id=" + id + ", type='" + type + "', weeksSize=" + (weeks != null ? weeks.size() : 0) + "}";
    }
}
