// package com.gopal.tictask.service;

// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.stereotype.Service;


// import lombok.RequiredArgsConstructor;

// @Service
// @RequiredArgsConstructor
// public class AuthService {
//     private final AuthenticationManager authenticationManager;
//     private final UserDetailsService userDetailsService;


//     public String login(String email, String password) {
//          authenticationManager.authenticate(
//             new UsernamePasswordAuthenticationToken(email, password)
//         );
//         var userdetails = userDetailsService.loadUserByUsername(email);
//         return "Login Success done "+userdetails;
//     }

// }
