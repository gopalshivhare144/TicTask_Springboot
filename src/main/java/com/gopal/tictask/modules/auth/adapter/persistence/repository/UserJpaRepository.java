package com.gopal.tictask.modules.auth.adapter.persistence.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gopal.tictask.modules.auth.domain.model.User;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
