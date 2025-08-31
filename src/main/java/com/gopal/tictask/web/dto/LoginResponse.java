package com.gopal.tictask.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

// Response for login containing token and user info
@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private Long id;
    private String email;
    private String role;
}
