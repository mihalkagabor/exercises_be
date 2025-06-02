package org.mihalka.exercises_be.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwUtil {

    //Titkos kulcs a token aláíráshoz (minimum 256bit hosszú)
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    //Token élettartama (milisecundum)
    private final long expiration=3600000; //1óra

    //Token generálása
    public String generateToken(String username){
        Date now= new Date();
        Date expiryDate=new Date(now.getTime()+expiration);

        return Jwts.builder()
                .setSubject(username) //a subject mezőbe menthetjük el a felhasználó nevet, vagy email címet.
                .setIssuedAt(now) //mikor készült a token
                .setExpiration(expiryDate) //token lejárati ideje
                . signWith(key) //aláírjuk a kulccsal
                .compact();
    }

    //Felhasználónév kivétele a tokenből
    public String extractUsername(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

//Token érvényességének ellenőrzése
    public boolean vlidateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // Hibás vagy lejárt token
            System.out.println("Invalid JWT token: " + e.getMessage());
            return false;
        }
    }


}
