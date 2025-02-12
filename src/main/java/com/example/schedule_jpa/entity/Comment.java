package com.example.schedule_jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Setter
    private User user;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    @Setter
    private Schedule schedule;

    public Comment(String contents) {
        this.contents = contents;
    }
}
