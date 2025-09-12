package com.gopal.tictask.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.gopal.tictask.security.CustomUserDetailsService;
import com.gopal.tictask.security.JwtAuthenticationFilter;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

/**
 * Security configuration:
 * - Stateless JWT auth
 * - JSON 401 responses
 * - Registers JwtAuthenticationFilter
 */

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
        //Disable CSRF since we’re stateless (no cookies/session)
                .csrf(csrf -> csrf.disable())
            //Make session management STATELESS (important for JWT)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //Customize 401 response
            .exceptionHandling(ex -> ex
                    .authenticationEntryPoint((request, response, authException) ->
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
                    )
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/api/auth/**",
                    "/say-hello",
                    "/v3/api-docs/**",
                    "/swagger-ui.html",
                    "/swagger-ui/**"
                ).permitAll()
                .anyRequest().authenticated()
                )
            //Register authentication provider
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Uses DAO-based authentication → fetch user from DB via CustomUserDetailsService.
	// Passwords are checked using BCryptPasswordEncoder.
    @Bean
    public AuthenticationProvider authenticationProvider() {
        // use constructor with password encoder (avoid deprecated no-arg ctor)
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    //Needed for AuthenticationService when logging in (it checks credentials).
	//Delegates to the configured AuthenticationProvider.

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
//Ensures passwords in DB are hashed with BCrypt (not plain text).
    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
}