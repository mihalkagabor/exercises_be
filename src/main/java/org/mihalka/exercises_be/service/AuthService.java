package org.mihalka.exercises_be.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;

    public AuthService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public Authentication authenticate(String identifier, String password) {
        Authentication auth = new UsernamePasswordAuthenticationToken(identifier, password);
        return authenticationManager.authenticate(auth);
    }
}

