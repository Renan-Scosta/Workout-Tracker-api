package com.renan.workout_tracker.repositories;

import com.renan.workout_tracker.domain.Exercise;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ExerciseRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Test
    @DisplayName("You should search for exercise lists by filtering by category.")
    void mustSearchExerciseByCategory() {
        // 1. Arrange (preparando o catálogo no banco em memoria)
        Exercise ex1 = new Exercise();
        ex1.setName("Supino reto");
        ex1.setCategory("Chest");
        ex1.setDescription("exercicio para parte superior do peito");
        entityManager.persist(ex1);

        Exercise ex2 = new Exercise();
        ex2.setName("Agachamento livre");
        ex2.setCategory("Legs");
        ex2.setDescription("exercicío focado para aumento do quadricipes");
        entityManager.persist(ex2);

        Exercise ex3 = new Exercise();
        ex3.setName("Crucifixo");
        ex3.setCategory("Chest");
        ex3.setDescription("exercicio para parte superior do peito");
        entityManager.persist(ex3);


        // 2. act (O método que vamos precisar criar no repositório)
        List<Exercise> chestExercise = exerciseRepository.findByCategory("Chest");


        // 3. Assert (Verificando se trouxe apenas os dois de peito)
        assertThat(chestExercise).hasSize(2);
        assertThat(chestExercise).extracting(Exercise::getName).
                containsExactlyInAnyOrder("Supino reto", "Crucifixo");
    }

}
