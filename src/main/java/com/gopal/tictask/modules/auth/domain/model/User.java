package com.gopal.tictask.modules.auth.domain.model;

public class User {
    private Long id;
    private String email;
    private String password;
    private Role roles;

    public User(Long id, String email, String password, Role roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    public Role getRoles() {
        return roles;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }
    


    
}
