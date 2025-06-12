package org.mihalka.exercises_be.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtService {

    //Titkos kulcs a token aláíráshoz (minimum 256bit hosszú)
    private final String SECRET_KEY = "nagyonTitkosKulcsNagyonTitkosKulcs123";

    //Token élettartama (milisecundum)
    private final long expiration=3600000; //1óra

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(String username){
        Date now = new Date();
        System.out.println("Current time: " + now);
        Date expiryDate = new Date(now.getTime() + expiration);
        System.out.println("Expiry time: " + expiryDate);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    //Felhasználónév kivétele a tokenből
    public String extractUsername(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

//Token érvényességének ellenőrzése
    public boolean validateToken(String token) {
        try {
            System.out.println("getExpiration(): " + getClaims(token).getExpiration());
            System.out.println("Instant.now(): " + java.time.Instant.now());
            System.out.println("Token expired? " + isTokenExpired(token));

            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // Hibás vagy lejárt token
            System.out.println("Invalid JWT token: " + e.getMessage());
            return false;
        }
    }
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().toInstant().isBefore(java.time.Instant.now());
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
