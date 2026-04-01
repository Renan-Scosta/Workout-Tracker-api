package com.renan.workout_tracker.repositories;

import com.renan.workout_tracker.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // O retorno Optional evita o erro NullPointerException se
    // o usuário digitar o email errado no login

    Optional<User> findByEmail(String email);

}
