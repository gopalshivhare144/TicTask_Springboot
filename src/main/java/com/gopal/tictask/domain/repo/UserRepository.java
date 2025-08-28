package com.gopal.tictask.domain.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gopal.tictask.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
    
} 
