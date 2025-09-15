package com.gopal.tictask.modules.auth.adapter.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginResponseDto {
    private Long id;
    private String email;
    private String roles;
    private String token;

}
