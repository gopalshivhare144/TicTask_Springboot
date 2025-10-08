package com.gopal.tictask.modules.auth.domain.model;

import java.time.Instant;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
   
    private Long id;

    private String email;

    private String password;

    private Roles roles = Roles.USER;

    private Instant createdAt;

}
