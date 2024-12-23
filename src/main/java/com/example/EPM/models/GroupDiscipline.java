package com.example.EPM.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "groupdiscipline")
@Data
public class GroupDiscipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private String number;

    @OneToMany(mappedBy = "groupDiscipline", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Discipline> discipline;

    @ManyToOne
    @JoinColumn(name = "week_id")
    private Week week;

}
