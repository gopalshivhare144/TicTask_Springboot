// package com.gopal.tictask.security;

// import java.util.Arrays;
// import java.util.stream.Collectors;

// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import com.gopal.tictask.domain.model.User;
// import com.gopal.tictask.domain.repo.UserRepository;

// import lombok.RequiredArgsConstructor;

// @Service
// @RequiredArgsConstructor
// public class CustomUserDetailsService  implements UserDetailsService{
//     private final UserRepository userRepository;

// @Override
//     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//         User user = userRepository.findByEmail(email)
//                 .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//         var authorities = Arrays.stream(user.getRoles().split(","))
//                 .map(String::trim)
//                 .filter(s -> !s.isEmpty())
//                 .map(SimpleGrantedAuthority::new)
//                 .collect(Collectors.toList());
//         return new org.springframework.security.core.userdetails.User(
//                 user.getEmail(), user.getPassword(), authorities
//         );
//     }


// }
