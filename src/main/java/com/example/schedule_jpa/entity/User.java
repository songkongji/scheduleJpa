package com.example.schedule_jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotBlank
    @Size(min = 1, max = 20)
    private String userName;

    @Email
    @Column(unique = true, nullable = false)
    @Setter
    @NotBlank
    private String email;

    @Column(nullable = false)
    @NotBlank
    @Size(min = 4, max = 20)
    private String password;

    public User() {
    }

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
}
