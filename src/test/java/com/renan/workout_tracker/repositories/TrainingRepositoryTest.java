package com.renan.workout_tracker.repositories;
import com.renan.workout_tracker.domain.Training;
import com.renan.workout_tracker.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TrainingRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TrainingRepository trainingRepository;

    @Test
    @DisplayName("must save a successful workout to the database")
    void mustSaveASuccessfulWorkout() {
        // 1. Arrange (preparar dados)
        User user = new User();
        user.setEmail("test@dominio.com");
        user.setUserName("atleta123");
        user.setPassword("password123");
        entityManager.persist(user); // salva o usuário no banco em memória

        Training training = new Training();
        training.setUser(user);
        training.setScheduledDateTime(LocalDateTime.now());
        training.setComments("Foco em Hipertrofia");
        training.setStatus("PENDING");

        // 2. Act (executar a ação que queremos testar)
        Training savedWorkout = trainingRepository.save(training);

        // 3. Assert (Verificar se o resultado é o esperado)
        assertThat(savedWorkout).isNotNull();
        assertThat(savedWorkout.getId()).isGreaterThan(0);
        assertThat(savedWorkout.getComments().equals("Foco em Hipertrofia"));

    }

}
