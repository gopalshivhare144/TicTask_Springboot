package com.gopal.tictask.modules.auth.adapter.web.dto.response;

import lombok.*;

@Data
public class LoginResponseDto {
    private Long id;
    private String email;
    private String roles;
    private String token;
}
