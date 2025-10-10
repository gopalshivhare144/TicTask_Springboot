package com.gopal.tictask.infrastructure.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.gopal.tictask.modules.auth.application.port.outbound.TokenServicePort;
import com.gopal.tictask.modules.auth.domain.model.User;

import java.security.Key;
import java.util.Date;

@Component
public class JwtServiceImpl implements TokenServicePort {

    private static final String SECRET_KEY = "YbR1ds2yqz1PZv4tNQwUjs1LBw3Hk6FbdyKQ6Fh5jTylrFevqOSm7QhK4AdkQqD9q9BkbT7Z7rxJYAbU1U5gOw==";
    private static final long EXPIRATION = 1000 * 60 * 60; // 1 hour

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    @Override
    public String generateToken(User user) {
        System.out.println("UserId issue impl" + user.getId());
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("id", user.getId())  
                .claim("role", user.getRoles().name())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    @Override
    public Long extractUserId(String token) {
        Object value = getClaims(token).get("id");
        if (value instanceof Number) {
            return ((Number) value).longValue();
        }
        return null;
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractRole(String token) {
        return getClaims(token).get("role", String.class);
    }


}