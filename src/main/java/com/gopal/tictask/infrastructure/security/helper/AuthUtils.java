// package com.gopal.tictask.infrastructure.security.helper;

// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.stereotype.Component;

// import com.gopal.tictask.infrastructure.security.CustomUserDetails;
// import com.gopal.tictask.modules.auth.domain.model.User;

// @Component
// public class AuthUtils {

//     public Long getCurrentUserId() {
//         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

//         if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
//             CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
//             return userDetails.getUser().getId();
//         }
//         return null;
//     }

//     public User getCurrentUser() {
//         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

//         if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
//             CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
//             return userDetails.getUser();
//         }
//         return null;
//     }

//     public String getCurrentUserEmail() {
//         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

//         if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
//             CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
//             return userDetails.getUser().getEmail();
//         }
//         return null;
//     }
// }
