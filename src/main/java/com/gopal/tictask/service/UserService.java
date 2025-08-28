package com.gopal.tictask.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gopal.tictask.domain.model.User;
import com.gopal.tictask.domain.repo.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    // private final PasswordEncoder passwordEncoder;


@Transactional
    public User createUser(String email, String rawPassword) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already registered");
        }
        User user = User.builder()
                .email(email)
                .password(rawPassword)
                .roles("USER")
                .build();

        return userRepository.save(user);
    }
    
    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }

    
}
