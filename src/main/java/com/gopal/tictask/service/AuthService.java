package com.gopal.tictask.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.gopal.tictask.security.CustomUserDetailsService;
import com.gopal.tictask.security.JwtUtils;

import lombok.RequiredArgsConstructor;

//Auth service responsible for authenticating credentials and issuing JWT.
@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService; // or UserDetailsService
    private final JwtUtils jwtUtils;

    public String login(String email, String password) {
        // Authenticate; will throw BadCredentialsException if fail
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(email, password)
        );

        // load user and generate token
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        return jwtUtils.generateToken(userDetails.getUsername());
    }
}
