package com.gopal.tictask.modules.auth.application.service;

import org.springframework.transaction.annotation.Transactional;

import com.gopal.tictask.infrastructure.security.BCryptPasswordEncoderService;
import com.gopal.tictask.infrastructure.security.JwtServiceImpl;
import com.gopal.tictask.modules.auth.adapter.web.dto.request.LoginRequest;
import com.gopal.tictask.modules.auth.adapter.web.dto.request.SignupRequest;
import com.gopal.tictask.modules.auth.adapter.web.dto.response.LoginResponseDto;
import com.gopal.tictask.modules.auth.adapter.web.mapper.UserWebMapper;
import com.gopal.tictask.modules.auth.application.exception.InvalidCredentialsException;
import com.gopal.tictask.modules.auth.application.exception.UserAlreadyExistsException;
import com.gopal.tictask.modules.auth.application.exception.UserNotFoundException;
import com.gopal.tictask.modules.auth.application.port.inbound.AuthUseCase;
import com.gopal.tictask.modules.auth.application.port.outbound.UserRepositoryPort;
import com.gopal.tictask.modules.auth.domain.model.User;
import com.gopal.tictask.shared.api.ApiResponse;

import lombok.AllArgsConstructor;

import java.util.Optional;

@Transactional
@AllArgsConstructor
public class AuthServiceImpl implements AuthUseCase {
    private final UserRepositoryPort userRepository;
    private final JwtServiceImpl jwtTokenProvider;
    private final BCryptPasswordEncoderService passwordEncoder;
    private final UserWebMapper userWebMapper;

    @Override
    public ApiResponse<String> signup(SignupRequest request) {

        Optional<User> existing = userRepository.findByEmail(request.getEmail());
        if (existing.isPresent()) {
            throw new UserAlreadyExistsException(request.getEmail());
        }
        User user = userWebMapper.signupRequestToDomain(request);
        user.setPassword(passwordEncoder.encrypt(request.getPassword()));
        userRepository.save(user);
        return ApiResponse.success("User registered successfully");

        // without mapper code
        // User user = new User(null,
        // request.getEmail(),
        // passwordEncoder.encode(request.getPassword()),
        // Role.USER);

        // userRepository.save(user);
        // return ApiResponse.success("User registered successfully");

    }

    @Override
    public ApiResponse<LoginResponseDto> login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException(request.getEmail()));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        LoginResponseDto response = userWebMapper.toLoginResponseDto(user);

        response.setToken(jwtTokenProvider.generateToken(user));

        return ApiResponse.success("Login successfully", response);
    }
}
