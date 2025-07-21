package org.mihalka.exercises_be.controller;

import jakarta.validation.Valid;
import org.mihalka.exercises_be.model.dto.LoginRequest;
import org.mihalka.exercises_be.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @GetMapping("/profile")
    public ResponseEntity<String> profile(Authentication authentication) {
        return ResponseEntity.ok("Hello, " + authentication.getName());
    }
    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestBody @Valid LoginRequest loginRequest){
       Map<String,String> tokens = authService.authenticate(loginRequest.getIdentifier(), loginRequest.getPassword());
        return ResponseEntity.ok(tokens);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Map<String,String>> refreshToken(@RequestBody Map<String,String> request){
        String refreshToken = request.get("refreshToken");
        String newAccessToken = authService.refreshAccessToken(refreshToken);
        return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        authService.logout(refreshToken); // itt törlöd a tokent
        return ResponseEntity.ok().build();
    }

}
