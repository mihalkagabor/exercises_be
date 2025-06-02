package org.mihalka.exercises_be.service;

import org.mihalka.exercises_be.model.entity.AppUserEntity;
import org.mihalka.exercises_be.model.entity.RefreshTokenEntity;
import org.mihalka.exercises_be.repository.AppUserRepository;
import org.mihalka.exercises_be.repository.RefreshTokenRepository;
import org.mihalka.exercises_be.security.JwtService;
import org.mihalka.exercises_be.security.RefreshTokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final AppUserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService, RefreshTokenService refreshTokenService, AppUserRepository userRepository, RefreshTokenRepository refreshTokenRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public Map<String, String> authenticate(String identifier, String password) {
        Authentication auth = new UsernamePasswordAuthenticationToken(identifier, password);
        Authentication authenticated = authenticationManager.authenticate(auth);

        String username = authenticated.getName();

        String accessToken = jwtService.generateToken(username);
        AppUserEntity user = userRepository.findByEmail(username)
                .or(() -> userRepository.findByName(username))
                .orElseThrow(() -> new RuntimeException("User not found after authentication"));

        String refreshToken = refreshTokenService.createRefreshToken(user).getToken();

        // Visszatérünk mindkét tokennel
        return Map.of(
                "accessToken", accessToken,
                "refreshToken", refreshToken
        );
    }

    // Új metódus: access token frissítése refresh token alapján
    public String refreshAccessToken(String refreshToken) {
        RefreshTokenEntity refreshTokenEntity = refreshTokenService.findByToken(refreshToken);

        if (refreshTokenService.isTokenExpired(refreshTokenEntity)) {
            throw new RuntimeException("Refresh token expired, please login again");
        }

        String username = refreshTokenEntity.getUser().getName() != null ?
                refreshTokenEntity.getUser().getName() :
                refreshTokenEntity.getUser().getEmail();

        return jwtService.generateToken(username);
    }

    public void logout(String refreshToken) {
        Optional<RefreshTokenEntity> tokenOpt = refreshTokenRepository.findByToken(refreshToken);
        if (tokenOpt.isPresent()) {
            refreshTokenRepository.delete(tokenOpt.get());
        } else {
            throw new IllegalArgumentException("Refresh token not found");
        }
    }
}

