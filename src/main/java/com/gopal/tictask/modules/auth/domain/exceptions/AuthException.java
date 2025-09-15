package com.gopal.tictask.modules.auth.domain.exceptions;

public class AuthException extends RuntimeException {
    public AuthException(String message) {
        super(message);
    }
}
