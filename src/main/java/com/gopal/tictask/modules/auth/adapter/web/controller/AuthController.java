package com.gopal.tictask.modules.auth.adapter.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gopal.tictask.infrastructure.security.JwtServiceImpl;
import com.gopal.tictask.modules.auth.adapter.web.dto.request.LoginRequest;
import com.gopal.tictask.modules.auth.adapter.web.dto.request.SignupRequest;
import com.gopal.tictask.modules.auth.adapter.web.dto.response.LoginResponseDto;
import com.gopal.tictask.modules.auth.adapter.web.mapper.UserWebMapper;
import com.gopal.tictask.modules.auth.application.port.inbound.AuthUseCase;
import com.gopal.tictask.modules.auth.domain.model.User;
import com.gopal.tictask.shared.api.ApiResponse;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthUseCase authUseCase;
    private final UserWebMapper userWebMapper;
    private final JwtServiceImpl jwtTokenProvider;


    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<Void>> signup(@Valid @RequestBody SignupRequest request) {

        User user = userWebMapper.signupRequestToDomain(request);
        authUseCase.signup(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("User Register Successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDto>> login(@Valid @RequestBody LoginRequest request) {
      
        User user = userWebMapper.loginRequestToDomain(request);
        User created = authUseCase.login(user);

        LoginResponseDto response = userWebMapper.toLoginResponseDto(created);
        response.setToken(jwtTokenProvider.generateToken(created));
        
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("User Login Successfully", response));
    }
}
