package com.gopal.tictask.modules.auth.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gopal.tictask.infrastructure.security.BCryptPasswordEncoderService;
import com.gopal.tictask.modules.auth.application.exception.InvalidCredentialsException;
import com.gopal.tictask.modules.auth.application.exception.UserAlreadyExistsException;
import com.gopal.tictask.modules.auth.application.exception.UserNotFoundException;
import com.gopal.tictask.modules.auth.application.port.inbound.AuthUseCase;
import com.gopal.tictask.modules.auth.application.port.outbound.UserRepositoryPort;
import com.gopal.tictask.modules.auth.domain.model.Roles;
import com.gopal.tictask.modules.auth.domain.model.User;

import lombok.AllArgsConstructor;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class AuthServiceImpl implements AuthUseCase {
    private final UserRepositoryPort userRepository;
    //private final JwtServiceImpl jwtTokenProvider;
    private final BCryptPasswordEncoderService passwordEncoder;
    //private final UserWebMapper userWebMapper;

    @Override
    public void signup(User user) {

        Optional<User> existing = userRepository.findByEmail(user.getEmail());
        if (existing.isPresent()) {
            throw new UserAlreadyExistsException(user.getEmail());
        }
       // User signUpRequest = userWebMapper.signupRequestToDomain(user);
        user.setPassword(passwordEncoder.encrypt(user.getPassword()));
        user.setRoles(Roles.USER);
        userRepository.save(user);
        

        // without mapper code
        // User user = new User(null,
        // request.getEmail(),
        // passwordEncoder.encode(request.getPassword()),
        // Role.USER);

        // userRepository.save(user);
        // return ApiResponse.success("User registered successfully");

    }

    @Override
    public User login(User user) {

        User isUserAvailable = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new UserNotFoundException(user.getEmail()));

        if (!passwordEncoder.matches(user.getPassword(), isUserAvailable.getPassword())) {
            throw new InvalidCredentialsException();
        }

        //LoginResponseDto response = userWebMapper.toLoginResponseDto(user);

        //response.setToken(jwtTokenProvider.generateToken(user));

        return isUserAvailable;
    }
}
