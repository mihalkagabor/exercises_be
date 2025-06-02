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
    //Token generálása
    public String generateToken(String username){
        Date now= new Date();
        Date expiryDate=new Date(now.getTime()+expiration);

        return Jwts.builder()
                .setSubject(username) //a subject mezőbe menthetjük el a felhasználó nevet, vagy email címet.
                .setIssuedAt(now) //mikor készült a token
                .setExpiration(expiryDate) //token lejárati ideje
                . signWith(getSigningKey(), SignatureAlgorithm.HS256) //aláírjuk a kulccsal
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
        return getClaims(token).getExpiration().before(new Date());
    }
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
