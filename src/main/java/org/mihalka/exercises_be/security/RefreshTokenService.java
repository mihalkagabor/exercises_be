package org.mihalka.exercises_be.security;

import org.mihalka.exercises_be.model.entity.AppUserEntity;
import org.mihalka.exercises_be.model.entity.RefreshTokenEntity;
import org.mihalka.exercises_be.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class RefreshTokenService {


    @Value("${app.jwt.refreshExpirationMs}")  // konfigurálható refresh token idő
    private Long refreshTokenDurationMs;

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }
    public RefreshTokenEntity createRefreshToken(AppUserEntity user) {
        RefreshTokenEntity refreshToken = new RefreshTokenEntity();

        refreshToken.setUser(user);
        refreshToken.setExpiryDate(Instant.now().plus(7, ChronoUnit.DAYS)); // <- ITT A LÉNYEG
        refreshToken.setToken(UUID.randomUUID().toString());  // véletlenszerű token

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public boolean isTokenExpired(RefreshTokenEntity token) {
        return token.getExpiryDate().isBefore(Instant.now());
    }

    public void deleteByUser(AppUserEntity user) {
        refreshTokenRepository.deleteByUser(user);
    }

    public RefreshTokenEntity findByToken(String token) {
        return refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Refresh token not found"));
    }
}


