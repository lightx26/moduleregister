package com.pet.moduleregister.application._shared;

import com.pet.moduleregister.entities.user.enums.AccountStatus;
import com.pet.moduleregister.entities.user.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthUser {
    private Long userId;
    private String userCode;
    private AccountStatus status;
    private UserRole role;
}
