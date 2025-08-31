package com.gopal.tictask.security;

import org.springframework.security.core.userdetails.UserDetails;


import com.gopal.tictask.domain.model.User;

import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
import java.util.List;

// Wraps our User entity for Spring Security.
public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) { this.user = user; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of((GrantedAuthority) () -> user.getRoles().name());
    }

    @Override public String getPassword() { return user.getPassword(); }
    @Override public String getUsername() { return user.getEmail(); }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
