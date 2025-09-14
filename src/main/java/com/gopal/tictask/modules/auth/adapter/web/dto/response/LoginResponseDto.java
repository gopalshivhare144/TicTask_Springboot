package com.gopal.tictask.modules.auth.adapter.web.dto.response;

public class LoginResponseDto {
    private Long id;
    private String email;
    private String roles;
    private String token;

    public LoginResponseDto(Long id, String email, String role, String token) {
        this.id = id;
        this.email = email;
        this.roles = role;
        this.token = token;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return roles; }
    public void setRole(String role) { this.roles = role; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}
