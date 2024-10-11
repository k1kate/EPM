package com.example.EPM.models;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Table(name = "roles")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public enum RoleName {
        ROLE_STUDENT,
        ROLE_TEACHER,
        ROLE_ADMIN
    }

}
