package com.gopal.tictask.modules.auth.adapter.web.dto.request;

import com.gopal.tictask.modules.auth.domain.model.Roles;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
public class SignupRequest {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 6, message = "Password must be at least 6 chars")
    private String password;

    @NotNull
    private Roles roles;

}
