// package com.gopal.tictask.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.ProviderManager;
// import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// import com.gopal.tictask.security.JwtAuthenticationFilter;

// import lombok.RequiredArgsConstructor;

// @Configuration
// @EnableMethodSecurity
// @RequiredArgsConstructor
// public class SecurityConfig {

//     private final JwtAuthenticationFilter jwtAuthenticationFilter;
//     private final UserDetailsService userDetailsService;

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//                 .csrf(csrf -> csrf.disable())
//                 .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                 .authorizeHttpRequests(auth -> auth
//                         .requestMatchers(
//                                 "/api/auth/**",
//                                 "/v3/api-docs/**",
//                                 "/swagger-ui.html",
//                                 "/swagger-ui/**")
//                         .permitAll()
//                         .requestMatchers(HttpMethod.GET, "/actuator/health").permitAll()
//                         .anyRequest().authenticated())
//                 .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//         return http.build();
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @SuppressWarnings("deprecation")
//     @Bean
//     public AuthenticationManager authenticationManager() {
//         DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//         provider.setUserDetailsService(userDetailsService);
//         provider.setPasswordEncoder(passwordEncoder());
//         return new ProviderManager(provider);
//     }

// // used for authentication in the controller
//     // @Bean
//     // public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//     //     return config.getAuthenticationManager();
//     // }


// }


