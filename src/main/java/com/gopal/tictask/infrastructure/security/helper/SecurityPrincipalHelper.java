// package com.gopal.tictask.infrastructure.security.helper;

// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.stereotype.Component;

// import com.gopal.tictask.infrastructure.security.CustomUserDetails;
// import com.gopal.tictask.infrastructure.security.UserPrincipal;

// @Component
// public class SecurityPrincipalHelper {

//     /**
//      * Returns current user id (from JWT principal). Null if unauthenticated.
//      */
//     public Long getCurrentUserId() {
//         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

//         if (authentication == null)
//             return null;

//         Object principal = authentication.getPrincipal();

//         if (principal instanceof UserPrincipal) {
//             return ((UserPrincipal) principal).getId();
//         } else if (principal instanceof CustomUserDetails) {
//             return ((CustomUserDetails) principal).getUser().getId();
//         }

//         return null;
//     }

//     public String getCurrentUserEmail() {
//         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//         if (authentication == null)
//             return null;

//         Object principal = authentication.getPrincipal();

//         if (principal instanceof UserPrincipal) {
//             return ((UserPrincipal) principal).getEmail();
//         } else if (principal instanceof CustomUserDetails) {
//             return ((CustomUserDetails) principal).getUser().getEmail();
//         }

//         return null;
//     }

//     public String getCurrentUserRole() {
//         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//         if (authentication == null)
//             return null;

//         Object principal = authentication.getPrincipal();

//         if (principal instanceof UserPrincipal) {
//             return ((UserPrincipal) principal).getRole();
//         } else if (principal instanceof CustomUserDetails) {
//             return ((CustomUserDetails) principal).getUser().getRoles().name();
//         }

//         return null;
//     }
// }
