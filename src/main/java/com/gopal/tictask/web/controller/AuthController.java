package com.gopal.tictask.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gopal.tictask.service.UserService;
import com.gopal.tictask.web.dto.ApiResponse;
import com.gopal.tictask.web.dto.SignupRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final UserService userService;
    //private final AuthService authService;


    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<Void>> signup(@Valid @RequestBody SignupRequest req) {
        userService.createUser(req.getEmail(), req.getPassword());

        return ResponseEntity.ok(ApiResponse.ok("Signup Succssful", null));
    }
    
    // @PostMapping("/login")
    // public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest req) {
    //     String token = userService.login(req.getEmail(), req.getPassword());
        
    //     return ResponseEntity.ok(ApiResponse.ok("Login Successfull", new AuthResponse(token)));
    // }
    
    



}
