package com.example.schedule_jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Setter
    private String userName;

    @Email
    @Column(unique = true)
    @Setter
    private String email;

    public User() {
    }

    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }
}
