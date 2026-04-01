package com.renan.workout_tracker.security;

import com.renan.workout_tracker.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class TokenServiceTest {

    private TokenService tokenService;

    @BeforeEach
    void setup() {
        tokenService = new TokenService();
    }

    @Test
    @DisplayName("It must generate a valid JWT token containing 3 parts.")
    void mustGenerateTokenSuccessfully() {

        User user = new User();
        user.setEmail("atleta@app.com");
        user.setUserName("Atleta");

        String token = tokenService.generatedToken(user);

        assertThat(token).isNotBlank();
        // um JWT real tem sempre 3 partes separadas por ponto (Header.Pauload.Signature)
        assertThat(token.split("\\.")).hasSize(3);
    }

    @Test
    @DisplayName("must validate the token and be able to read the email contained within it.")
    void mustExtractEmailFromToken() {

        User user = new User();
        user.setEmail("Segredo@app.com");
        String token = tokenService.generatedToken(user);

        String extractedEmail = tokenService.getSubject(token);

        assertThat(extractedEmail).isEqualTo("Segredo@app.com");

    }

}
