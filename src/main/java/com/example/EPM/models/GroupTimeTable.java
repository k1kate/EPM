package com.example.EPM.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "grouptimetable")
@Data
public class GroupTimeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "groupTimeTable", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TimeTable> timeTables = new ArrayList<>();
    @Override
    public String toString() {
        return "GroupTimeTable{id=" + id + ", timeTablesSize=" + (timeTables != null ? timeTables.size() : 0) + "}";
    }




}