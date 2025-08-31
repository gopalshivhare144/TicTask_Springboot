package com.gopal.tictask.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gopal.tictask.domain.model.Role;
import com.gopal.tictask.domain.model.User;
import com.gopal.tictask.domain.repo.UserRepository;
import com.gopal.tictask.exception.NotFoundException;

import lombok.RequiredArgsConstructor;

//User creation and lookup
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


@Transactional
    public User createUser(String email, String rawPassword) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already registered");
        }
        User user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(rawPassword))
                .roles(Role.USER)
                .build();
            System.out.println("uservalue "+ user.getRoles());
            System.out.println("uservalue "+ user.getEmail());
        return userRepository.save(user);
    }
    
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found for email: " + email));
    }

    
}
