package com.renan.workout_tracker.repositories;

import com.renan.workout_tracker.domain.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training, Long> {

    //por enquanto não irei colocar nada aqui
    //o JpaRepository já me dá o save(), findbyId(), delete(), etc.
    //pelo principio YAGNI do XP, só criamos métodos novos quando o sistema exigir

}
