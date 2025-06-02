package org.mihalka.exercises_be.controller;

import org.mihalka.exercises_be.model.dto.JwtResponse;
import org.mihalka.exercises_be.model.dto.LoginRequest;
import org.mihalka.exercises_be.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        try {
            String jwtToken = authService.authenticate(loginRequest.getIdentifier(), loginRequest.getPassword());
            return ResponseEntity.ok(new JwtResponse(jwtToken)); // <-- itt adjuk vissza a JWT-t
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed: " + e.getMessage());
        }
    }
    @GetMapping("/profile")
    public ResponseEntity<String> profile(Authentication authentication) {
        return ResponseEntity.ok("Hello, " + authentication.getName());
    }
}
