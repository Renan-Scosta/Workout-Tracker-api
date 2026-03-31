package com.renan.workout_tracker.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_trainings")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDateTime scheduledDateTime;

    @Column(columnDefinition = "TEXT")
    private String comments;

    // controla se o treino é ativo, pendente ou concluído
    @Column(nullable = false)
    private String status = "PENDING";

    @OneToMany(mappedBy = "training", cascade = CascadeType.ALL)
    private List<TrainingItem> items = new ArrayList<>();

}
