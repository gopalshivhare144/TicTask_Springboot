package com.gopal.tictask.modules.auth.application.port.outbound;

import java.util.Optional;

import com.gopal.tictask.modules.auth.domain.model.User;

public interface UserRepositoryPort {
    Optional<User> findByEmail(String email);
    
    Optional<User> findById(Long id);

    User save(User user);
}
