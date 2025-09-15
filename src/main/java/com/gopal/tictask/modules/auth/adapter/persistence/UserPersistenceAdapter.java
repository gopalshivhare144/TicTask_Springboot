package com.gopal.tictask.modules.auth.adapter.persistence;

import org.springframework.stereotype.Component;

import com.gopal.tictask.modules.auth.adapter.mapper.UserMapper;
import com.gopal.tictask.modules.auth.adapter.persistence.repository.UserJpaRepository;
import com.gopal.tictask.modules.auth.application.port.outbound.UserRepositoryPort;
import com.gopal.tictask.modules.auth.domain.model.User;

import java.util.Optional;

@Component
public class UserPersistenceAdapter implements UserRepositoryPort {

    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    public UserPersistenceAdapter(UserJpaRepository userJpaRepository, UserMapper userMapper) {
        this.userJpaRepository = userJpaRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email).map(userMapper::toDomain);
    }

    @Override
    public User save(User user) {
        User saved = userJpaRepository.save(userMapper.toEntity(user));
        return userMapper.toDomain(saved);
    }
}
