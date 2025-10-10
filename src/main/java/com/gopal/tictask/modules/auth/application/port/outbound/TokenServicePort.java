package com.gopal.tictask.modules.auth.application.port.outbound;

import org.springframework.security.core.userdetails.UserDetails;

import com.gopal.tictask.modules.auth.domain.model.User;

public interface TokenServicePort {
    String generateToken(User user);

    String extractUsername(String token);

    boolean isTokenValid(String token, UserDetails userDetails);

    Long extractUserId(String token);

    String extractRole(String token);
}
