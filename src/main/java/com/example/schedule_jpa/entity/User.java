package com.example.schedule_jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;

@Entity
@Getter
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Email
    @Column(unique = true)
    private String email;
}
