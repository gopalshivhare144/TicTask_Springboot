package com.gopal.tictask.modules.auth.application.port.outbound;

import com.gopal.tictask.modules.auth.domain.model.User;

public interface TokenProviderPort {
    String generateToken(User user);
}
