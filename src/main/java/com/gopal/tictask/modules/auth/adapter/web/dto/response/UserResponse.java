package com.gopal.tictask.modules.auth.adapter.web.dto.response;

public class UserResponse {
    private Long id;
    private String email;
    private String roles;

    public UserResponse(Long id, String email, String roles) {
        this.id = id;
        this.email = email;
        this.roles = roles;
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getRoles() { return roles; }
}
