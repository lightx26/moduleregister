package com.pet.moduleregister.infrastructure.adapters.in.web._shared.security;

import com.pet.moduleregister.entities.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuthUtils {
    public static Optional<AuthUser> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof AuthUser authUser)) {
            return Optional.empty();
        }
        return Optional.of(authUser);
    }

    public static AuthUser mapToAuthUser(User user) {
        return AuthUser.builder()
                .userId(user.getUserId())
                .userCode(user.getUserCode())
                .createdAt(user.getCreatedAt())
                .status(user.getStatus())
                .role(user.getRole())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .programId(user.getProgramId())
                .build();
    }
}
