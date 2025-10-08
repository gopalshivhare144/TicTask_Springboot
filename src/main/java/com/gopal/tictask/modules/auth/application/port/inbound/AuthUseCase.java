package com.gopal.tictask.modules.auth.application.port.inbound;

import com.gopal.tictask.modules.auth.domain.model.User;

public interface AuthUseCase {
    
    void signup(User user);

    User login(User user);
}
