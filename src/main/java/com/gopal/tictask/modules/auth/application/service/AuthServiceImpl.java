package com.gopal.tictask.modules.auth.application.service;
import org.springframework.transaction.annotation.Transactional;
import com.gopal.tictask.modules.auth.adapter.mapper.UserMapper;
import com.gopal.tictask.modules.auth.adapter.security.BCryptPasswordEncoderService;
import com.gopal.tictask.modules.auth.adapter.security.JwtTokenProvider;
import com.gopal.tictask.modules.auth.adapter.web.dto.request.LoginRequest;
import com.gopal.tictask.modules.auth.adapter.web.dto.request.SignupRequest;
import com.gopal.tictask.modules.auth.adapter.web.dto.response.LoginResponseDto;
import com.gopal.tictask.modules.auth.application.port.inbound.AuthUseCase;
import com.gopal.tictask.modules.auth.application.port.outbound.UserRepositoryPort;
import com.gopal.tictask.modules.auth.domain.exceptions.InvalidCredentialsException;
import com.gopal.tictask.modules.auth.domain.exceptions.UserAlreadyExistsException;
import com.gopal.tictask.modules.auth.domain.exceptions.UserNotFoundException;
import com.gopal.tictask.modules.auth.domain.model.User;
import com.gopal.tictask.shared.api.ApiResponse;

import java.util.Optional;


@Transactional
public class AuthServiceImpl implements AuthUseCase {
    private final UserRepositoryPort userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoderService passwordEncoder;
    private final UserMapper userMapper;

    public AuthServiceImpl(UserRepositoryPort userRepository,
            JwtTokenProvider jwtTokenProvider,
            BCryptPasswordEncoderService passwordEncoder,
            UserMapper userMapper) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public ApiResponse<String> signup(SignupRequest request) {
        Optional<User> existing = userRepository.findByEmail(request.getEmail());
        if (existing.isPresent()) {
            throw new UserAlreadyExistsException(request.getEmail());
        }

        // Map DTO → Domain
        User user = userMapper.signupRequestToDomain(request);

        // Encode password before saving

        user.setPassword(passwordEncoder.encrypt(request.getPassword()));

        // Save (Domain → Entity handled in repo)
        userRepository.save(user);
        return ApiResponse.success("User registered successfully");

        // without mapper code
        // User newUser = new User(
        // null,
        // request.getEmail(),
        // passwordEncoder.encrypt(request.getPassword()),
        // Role.USER
        // );
        // userRepository.save(newUser);
    }

    @Override
    public ApiResponse<LoginResponseDto> login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException(request.getEmail()));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        // Map Domain → Response DTO
        LoginResponseDto response = userMapper.toLoginResponse(user);
        response.setToken(jwtTokenProvider.generateToken(user));
        return ApiResponse.success("Login successfully", response);

        // String token = tokenProvider.generateToken(user);
        // return new LoginResponseDto(user.getId(), user.getEmail(),
        // user.getRoles().name(), token);
    }
}
