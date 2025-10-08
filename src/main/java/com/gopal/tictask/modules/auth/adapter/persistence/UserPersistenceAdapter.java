package com.gopal.tictask.modules.auth.adapter.persistence;

import org.springframework.stereotype.Component;

import com.gopal.tictask.modules.auth.adapter.mapper.UserEntityMapper;
import com.gopal.tictask.modules.auth.adapter.persistence.entity.UserEntity;
import com.gopal.tictask.modules.auth.adapter.persistence.repository.UserJpaRepository;
import com.gopal.tictask.modules.auth.application.port.outbound.UserRepositoryPort;
import com.gopal.tictask.modules.auth.domain.model.User;

import lombok.AllArgsConstructor;

import java.util.Optional;

@Component
@AllArgsConstructor
public class UserPersistenceAdapter implements UserRepositoryPort {

    private final UserJpaRepository userJpaRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email).map(userEntityMapper::toDomain); //convert UserEntity to domain

        // Optional<UserEntity> userEntityOpt = userJpaRepository.findByEmail(email);

        // if (userEntityOpt.isPresent()) {
        //     UserEntity userEntity = userEntityOpt.get();
        //     User user = userEntityMapper.toDomain(userEntity);
        //     return Optional.of(user);
        // } else {
        //     return Optional.empty();
        // }

    }
    
    @Override
    public Optional<User> findById(Long id) {
        return userJpaRepository.findById(id).map(userEntityMapper::toDomain); 
    }

    @Override
    public User save(User user) {
        UserEntity saved = userJpaRepository.save(userEntityMapper.toEntity(user));
        //domain object converted into persistence object (UserEntity)
        return userEntityMapper.toDomain(saved);
        // convert back into a domain object to return to the service/usecase layer

    }
}
