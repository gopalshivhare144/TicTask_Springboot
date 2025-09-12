package com.gopal.tictask.modules.auth.application.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gopal.tictask.modules.auth.adapter.mapper.UserMapper;
import com.gopal.tictask.modules.auth.adapter.web.dto.request.LoginRequest;
import com.gopal.tictask.modules.auth.adapter.web.dto.request.SignupRequest;
import com.gopal.tictask.modules.auth.adapter.web.dto.response.LoginResponseDto;
import com.gopal.tictask.modules.auth.application.port.AuthUseCase;
import com.gopal.tictask.modules.auth.application.port.TokenProviderPort;
import com.gopal.tictask.modules.auth.application.port.UserRepositoryPort;
import com.gopal.tictask.modules.auth.domain.exceptions.InvalidCredentialsException;
import com.gopal.tictask.modules.auth.domain.exceptions.UserAlreadyExistsException;
import com.gopal.tictask.modules.auth.domain.exceptions.UserNotFoundException;
import com.gopal.tictask.modules.auth.domain.model.Role;
import com.gopal.tictask.modules.auth.domain.model.User;
import com.gopal.tictask.modules.auth.domain.service.PasswordEncryptionService;
import java.util.Optional;
@Service
@Transactional
public class AuthServiceImpl implements AuthUseCase {
    private final UserRepositoryPort userRepository;
    private final TokenProviderPort tokenProvider;
    private final PasswordEncryptionService passwordEncoder;

    public AuthServiceImpl(UserRepositoryPort userRepository,
                       TokenProviderPort tokenProvider,
                       PasswordEncryptionService passwordEncoder,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void signup(SignupRequest request) {
        Optional<User> existing = userRepository.findByEmail(request.getEmail());
        if (existing.isPresent()) {
            throw new UserAlreadyExistsException(request.getEmail());
        }
        User newUser = new User(
                null,
                request.getEmail(),
                passwordEncoder.encrypt(request.getPassword()),
                Role.USER
        );
         userRepository.save(newUser);
    }
    @Override
    public LoginResponseDto login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException(request.getEmail()));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
           throw new InvalidCredentialsException();
        }
        String token = tokenProvider.generateToken(user);
        return new LoginResponseDto(user.getId(), user.getEmail(), user.getRoles().name(), token);
    }
}
