package com.renan.workout_tracker.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.renan.workout_tracker.domain.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    // Essa é a chave que tranca e destranca o token.
    // Deixarei por enquanto fixa para o teste passar
    // Depois, moverei isso para application.properties por segurança
    private String secretKey = "my-secret-key";

    public String generatedToken(User user) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            return JWT.create()
                    .withIssuer("workout-tracker") // Quem esta imitindo o token
                    .withSubject(user.getEmail()) // O "assunto" principal do token (o email do usuário)
                    .withExpiresAt(generateExpirationDate()) // quando o token vence
                    .sign(algorithm); // assina e gera a String final
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token JWT", exception);
        }

    }

    public String getSubject(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            return JWT.require(algorithm)
                    .withIssuer("workout-tracker") // garante fomos nós que emitimos
                    .build()
                    .verify(token) // Descriptografa e verificaa se não está vencido
                    .getSubject(); // pega o email de volta
        } catch (JWTVerificationException exception) {
            // Se o token for inválido, falso ou estiver vencido, retorna vazio.
            // Isso vai forçar o usuário a fazer login novamente no aplicativo
            return "";
        }

    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
