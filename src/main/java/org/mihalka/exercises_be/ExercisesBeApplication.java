package org.mihalka.exercises_be;

import org.mihalka.exercises_be.security.JwtService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExercisesBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExercisesBeApplication.class, args);
        System.out.println("Hello world!");
        JwtService jwtService = new JwtService();
        String token = jwtService.generateToken("testuser");
        System.out.println("Generated token: " + token);

        // Azonnal ellenőrzés:
        String username = jwtService.extractUsername(token);
        System.out.println("Username from token: " + username);
    }

}
