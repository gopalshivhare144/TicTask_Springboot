package com.gopal.tictask.modules.auth.domain.service;

public interface PasswordEncryptionService {
    String encrypt(String rawPassword);
    boolean matches(String rawPassword, String encryptedPassword);
}
