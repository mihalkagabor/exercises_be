package org.mihalka.exercises_be.service;

import org.mihalka.exercises_be.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtUtil;

    public AuthService(AuthenticationManager authenticationManager, JwtService jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public String authenticate(String identifier, String password) {
        Authentication auth = new UsernamePasswordAuthenticationToken(identifier, password);
        Authentication authenticated = authenticationManager.authenticate(auth);

        // JWT generálás
        return jwtUtil.generateToken(authenticated.getName()); // vagy authenticated.getPrincipal() ha email-t tárolsz benne
    }
}

