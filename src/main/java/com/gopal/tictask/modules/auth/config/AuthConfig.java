package com.gopal.tictask.modules.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gopal.tictask.infrastructure.security.BCryptPasswordEncoderService;
import com.gopal.tictask.infrastructure.security.JwtServiceImpl;
import com.gopal.tictask.modules.auth.adapter.web.mapper.UserWebMapper;
import com.gopal.tictask.modules.auth.application.port.inbound.AuthUseCase;
import com.gopal.tictask.modules.auth.application.port.outbound.UserRepositoryPort;
import com.gopal.tictask.modules.auth.application.service.AuthServiceImpl;

@Configuration
public class AuthConfig {

    @Bean
    public AuthUseCase authUseCase(
            UserRepositoryPort userRepositoryPort,
            JwtServiceImpl tokenProviderPort,
            BCryptPasswordEncoderService passwordEncoder,
            UserWebMapper userMapper) {
        return new AuthServiceImpl(userRepositoryPort, tokenProviderPort, passwordEncoder, userMapper);
    }
}
