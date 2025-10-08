package com.gopal.tictask.modules.auth.adapter.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
public class LoginRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String password;

}
