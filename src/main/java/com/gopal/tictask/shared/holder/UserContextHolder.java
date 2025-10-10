package com.gopal.tictask.shared.holder;

import org.springframework.stereotype.Component;

@Component
public class UserContextHolder {

    private static final ThreadLocal<Long> userIdHolder = new ThreadLocal<>();
    private static final ThreadLocal<String> userEmailHolder = new ThreadLocal<>();

    public static void setUser(Long userId, String email) {
        userIdHolder.set(userId);
        userEmailHolder.set(email);
    }

    public static Long getUserId() {
        return userIdHolder.get();
    }

    public static String getUserEmail() {
        return userEmailHolder.get();
    }

    public static void clear() {
        userIdHolder.remove();
        userEmailHolder.remove();
    }
}
