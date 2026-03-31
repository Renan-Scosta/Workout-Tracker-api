package com.renan.workout_tracker.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_trainings_items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class TrainingItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "training_id", nullable = false)
    private Training training;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @Column(nullable = false)
    private Integer series;


    @Column(nullable = false)
    private Integer repetitions;

    @Column(nullable = false)
    private Double weight;

}
