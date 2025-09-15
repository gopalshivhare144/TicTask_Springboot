package com.gopal.tictask.modules.auth.application.port.inbound;

import com.gopal.tictask.modules.auth.adapter.web.dto.request.LoginRequest;
import com.gopal.tictask.modules.auth.adapter.web.dto.request.SignupRequest;
import com.gopal.tictask.modules.auth.adapter.web.dto.response.LoginResponseDto;
import com.gopal.tictask.shared.api.ApiResponse;

public interface AuthUseCase {
    ApiResponse<String> signup(SignupRequest request);
   
    ApiResponse<LoginResponseDto>login(LoginRequest request);
}
