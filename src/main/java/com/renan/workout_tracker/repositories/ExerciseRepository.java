package com.renan.workout_tracker.repositories;

import com.renan.workout_tracker.domain.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    // O Spring vai gerar o SQL automaticamente por causa do prefixo "findyBy"
    // Somado ao nome do Atributo "category"
    List<Exercise> findByCategory(String category);

}
