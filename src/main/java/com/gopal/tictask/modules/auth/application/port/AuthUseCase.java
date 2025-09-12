package com.gopal.tictask.modules.auth.application.port;

import com.gopal.tictask.modules.auth.adapter.web.dto.request.LoginRequest;
import com.gopal.tictask.modules.auth.adapter.web.dto.request.SignupRequest;
import com.gopal.tictask.modules.auth.adapter.web.dto.response.LoginResponseDto;

public interface AuthUseCase {
    void signup(SignupRequest request);
    LoginResponseDto login(LoginRequest request);
}
