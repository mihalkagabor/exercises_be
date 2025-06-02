package org.mihalka.exercises_be.service;

import org.mihalka.exercises_be.security.JwUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwUtil jwtUtil;

    public AuthService(AuthenticationManager authenticationManager, JwUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public String authenticate(String identifier, String password) {
        Authentication auth = new UsernamePasswordAuthenticationToken(identifier, password);
        Authentication authenticated = authenticationManager.authenticate(auth);

        // Ha sikeres, generáljuk a JWT-t
        return jwtUtil.generateToken(authenticated.getName());
    }
}

