package com.gopal.tictask.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gopal.tictask.domain.model.User;
import com.gopal.tictask.service.AuthService;
import com.gopal.tictask.service.UserService;
import com.gopal.tictask.web.dto.ApiResponse;
import com.gopal.tictask.web.dto.AuthResponse;
import com.gopal.tictask.web.dto.LoginRequest;
import com.gopal.tictask.web.dto.LoginResponse;
import com.gopal.tictask.web.dto.SignupRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//Authentication endpoints: signup & login
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;
    private final UserService userService;


 
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<AuthResponse>> signup(@Valid @RequestBody SignupRequest request) {
        userService.createUser(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(ApiResponse.ok("Signup Succssful", null));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request) {
    
        String token = authService.login(request.getEmail(), request.getPassword());
    
        User user = userService.getByEmail(request.getEmail());

    LoginResponse response = new LoginResponse(
            token,
            user.getId(),
            user.getEmail(),
            user.getRoles().name()  // if Enum
    );
    return ResponseEntity.ok(ApiResponse.ok("Login Successfull", response));
}

}
