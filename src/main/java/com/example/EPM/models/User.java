package com.example.EPM.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data //геттеры и сеттеры и конструктор
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private boolean enabled;

    @ManyToOne(fetch = FetchType.EAGER) // Только одна роль
    @JoinColumn(name = "role_id") // Привязка к таблице ролей
    private Role role;
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role=" + role + '\'' +
                '}';
    }

    private boolean isTimeTableLoaded = false;

    public void setTimeTableLoaded(boolean timeTableLoaded) {
        isTimeTableLoaded = timeTableLoaded;
    }

    @Column(name = "avatar", columnDefinition = "bytea")
    private byte[] avatar;
}
