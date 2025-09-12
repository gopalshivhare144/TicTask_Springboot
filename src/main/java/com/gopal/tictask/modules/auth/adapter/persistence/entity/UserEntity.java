package com.gopal.tictask.modules.auth.adapter.persistence.entity;

import jakarta.persistence.*;
import com.gopal.tictask.modules.auth.domain.model.Role;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

   
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role roles = Role.USER;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role getRoles() { return roles; }
    public void setRoles(Role roles) { this.roles = roles; }
}
