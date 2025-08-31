package com.gopal.tictask.security;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;


import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

/**
 * Utility for generating and validating JWTs.
 * Expects a 32+ byte secret for HS256.
 */

@Component
public class JwtUtils {

    private final Key key;
    private final long jwtExpiration;

    public JwtUtils(@Value("${app.jwt.secret}") String secret,
            @Value("${app.jwt.expiration}") long jwtExpiration) {

        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);

        if (keyBytes.length < 32) { // HS256 requires 256 bits = 32 bytes
            throw new IllegalArgumentException("JWT secret is too short. Use at least 32 bytes");
        }

        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.jwtExpiration = jwtExpiration;
    }
    
//Generate token with subject = email and expiration
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    //Parse token and return email (subject)
    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    //Validate token (signature + expiry)
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // optionally log e.getMessage()
            return false;
        }
    }
}