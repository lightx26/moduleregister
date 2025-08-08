package com.pet.moduleregister.infrastructure.adapters.in.web._shared.security;

import com.pet.moduleregister.entities.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuthUtils {
    public static Optional<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof User)) {
            return Optional.empty();
        }
        return Optional.of((User) authentication.getPrincipal());
    }
}
