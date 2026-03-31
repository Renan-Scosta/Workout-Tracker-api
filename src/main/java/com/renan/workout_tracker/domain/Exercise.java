package com.renan.workout_tracker.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_exercises")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String category; // example: força, cardio, peito, costas


}
