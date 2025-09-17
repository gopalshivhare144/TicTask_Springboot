package com.gopal.tictask.modules.auth.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class User {
   
    private Long id;

    private String email;

    private String password;

    private Roles roles = Roles.USER;

}
