package com.gopal.tictask.modules.auth.application.service;
import org.springframework.transaction.annotation.Transactional;

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
import com.gopal.tictask.shared.security.BCryptPasswordEncoderService;
import com.gopal.tictask.shared.security.JwtTokenProvider;

import lombok.AllArgsConstructor;

import java.util.Optional;


@Transactional
@AllArgsConstructor
public class AuthServiceImpl implements AuthUseCase {
    private final UserRepositoryPort userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoderService passwordEncoder;
    private final UserWebMapper userWebMapper;


    @Override
    public ApiResponse<String> signup(SignupRequest request) {
        Optional<User> existing = userRepository.findByEmail(request.getEmail());
        if (existing.isPresent()) {
            throw new UserAlreadyExistsException(request.getEmail());
        }

        // Map DTO → Domain
        User user = userWebMapper.signupRequestToDomain(request);

        // Encode password before saving

        user.setPassword(passwordEncoder.encrypt(request.getPassword()));

        // Save (Domain → Entity handled in repo)
        userRepository.save(user);
        return ApiResponse.success("User registered successfully");

        // without mapper code, this is new code need to check with chatgpt

        // User user = new User(null,
        //         request.getEmail(),
        //         passwordEncoder.encode(request.getPassword()),
        //         Role.USER);

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

        // Map Domain → Response DTO
        LoginResponseDto response = userWebMapper.toLoginResponse(user);

        response.setToken(jwtTokenProvider.generateToken(user));

        return ApiResponse.success("Login successfully", response);

        // String token = tokenProvider.generateToken(user);
        // return new LoginResponseDto(user.getId(), user.getEmail(),
        // user.getRoles().name(), token);
    }
}
