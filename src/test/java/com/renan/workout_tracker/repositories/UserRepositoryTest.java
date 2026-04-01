package com.renan.workout_tracker.repositories;

import com.renan.workout_tracker.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Must be able to successfully find a user by email.")
    void mustFindUserByEmail() {
        // 1. Arrange (Criando e salvando um usuário de teste)
        User user = new User();
        user.setEmail("renan@teste.com");
        user.setPassword("senhasegura123");
        user.setUserName("Renan Costa");
        entityManager.persist(user);

        // 2. Act. (A busca pelo email)
        // Optinional pois o email digitado pode não existir no banco
        Optional<User> userFound = userRepository.findByEmail("renan@teste.com");

        // 3. Assert (Garantindo que achou a pessoa certa)
        assertThat(userFound).isPresent();
        assertThat(userFound.get().getUserName()).isEqualTo("Renan Costa");
    }

    @Test
    @DisplayName("It should return empty when searching for a non-existent email address.")
    void UserShouldNotBeFoundIfEmailDoesNotExist() {
        // 1. Arrange (O banco começa vazio, não salvamos ninguém)

        // 2. Act.
        Optional<User> userFound = userRepository.findByEmail("renan@teste.com");

        // 3. Assert (Garantindo que não quebrou e apenas retornou vazio)
        assertThat(userFound).isEmpty();

    }

}
