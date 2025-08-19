package com.pet.moduleregister.infrastructure.adapters.in.web._shared.security;

import com.pet.moduleregister.entities.user.enums.AccountStatus;
import com.pet.moduleregister.entities.user.enums.UserRole;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
public class AuthUser {
    private Long userId;
    private String userCode;
    private Instant createdAt;
    private AccountStatus status;
    private UserRole role;

    private String firstName;
    private String lastName;
    private String email;

    private Long programId;
}
