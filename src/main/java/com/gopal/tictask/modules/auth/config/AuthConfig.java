package com.gopal.tictask.modules.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gopal.tictask.modules.auth.adapter.mapper.UserEntityMapper;
import com.gopal.tictask.modules.auth.adapter.persistence.UserPersistenceAdapter;
import com.gopal.tictask.modules.auth.adapter.persistence.repository.UserJpaRepository;
import com.gopal.tictask.modules.auth.adapter.web.mapper.UserWebMapper;
import com.gopal.tictask.modules.auth.application.port.inbound.AuthUseCase;
import com.gopal.tictask.modules.auth.application.port.outbound.TokenProviderPort;
import com.gopal.tictask.modules.auth.application.port.outbound.UserRepositoryPort;
import com.gopal.tictask.modules.auth.application.service.AuthServiceImpl;
import com.gopal.tictask.shared.security.BCryptPasswordEncoderService;
import com.gopal.tictask.shared.security.JwtTokenProvider;

@Configuration
public class AuthConfig {

    @Bean
    public UserRepositoryPort userRepositoryPort(UserJpaRepository userJpaRepository, UserEntityMapper userMapper) {
        return new UserPersistenceAdapter(userJpaRepository, userMapper);
    }

    @Bean
    public TokenProviderPort tokenProviderPort() {
        return new JwtTokenProvider();
    }

    @Bean
    public AuthUseCase authUseCase(
            UserRepositoryPort userRepositoryPort,
            JwtTokenProvider tokenProviderPort,
            BCryptPasswordEncoderService passwordEncoder,
            UserWebMapper userMapper) {
        return new AuthServiceImpl(userRepositoryPort, tokenProviderPort, passwordEncoder, userMapper);
    }
}
