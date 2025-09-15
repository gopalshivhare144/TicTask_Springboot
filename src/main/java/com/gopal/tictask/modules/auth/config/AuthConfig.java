package com.gopal.tictask.modules.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gopal.tictask.modules.auth.adapter.mapper.UserMapper;
import com.gopal.tictask.modules.auth.adapter.persistence.UserPersistenceAdapter;
import com.gopal.tictask.modules.auth.adapter.persistence.repository.UserJpaRepository;
import com.gopal.tictask.modules.auth.adapter.security.BCryptPasswordEncoderService;
import com.gopal.tictask.modules.auth.adapter.security.JwtTokenProvider;
import com.gopal.tictask.modules.auth.application.port.inbound.AuthUseCase;
import com.gopal.tictask.modules.auth.application.port.outbound.TokenProviderPort;
import com.gopal.tictask.modules.auth.application.port.outbound.UserRepositoryPort;
import com.gopal.tictask.modules.auth.application.service.AuthServiceImpl;

@Configuration
public class AuthConfig {

    @Bean
    public UserRepositoryPort userRepositoryPort(UserJpaRepository userJpaRepository, UserMapper userMapper) {
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
            UserMapper userMapper) {
        return new AuthServiceImpl(userRepositoryPort, tokenProviderPort, passwordEncoder, userMapper);
    }
}
