package com.gopal.tictask.modules.auth.adapter.web.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gopal.tictask.modules.auth.adapter.web.dto.request.LoginRequest;
import com.gopal.tictask.modules.auth.adapter.web.dto.request.SignupRequest;
import com.gopal.tictask.modules.auth.adapter.web.dto.response.LoginResponseDto;
import com.gopal.tictask.modules.auth.application.port.inbound.AuthUseCase;
import com.gopal.tictask.shared.api.ApiResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthUseCase authUseCase;

    public AuthController(AuthUseCase authUseCase) {
        this.authUseCase = authUseCase;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<String>> signup(@Valid @RequestBody SignupRequest request) {
        System.out.println("AuthSection ====>request " + request.toString());
        return ResponseEntity.ok(authUseCase.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDto>> login(@Valid @RequestBody LoginRequest request) {
      
        return ResponseEntity.ok(authUseCase.login(request));
    }
}
