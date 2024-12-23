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

    @OneToMany(mappedBy = "role")
    private List<User> users;

    public enum RoleName { //фиксированный набор заранее известных констант
        ROLE_STUDENT,
        ROLE_TEACHER,
        ROLE_ADMIN
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }


}
