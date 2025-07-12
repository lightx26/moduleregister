package com.pet.moduleregister.domain.modules.user.model;

import com.pet.moduleregister.domain.modules.user.enums.AccountStatus;
import com.pet.moduleregister.domain.modules.user.enums.Gender;
import com.pet.moduleregister.domain.modules.user.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    // Account information
    private String userId;
    private String password;
    private Instant createdAt;
    private AccountStatus status;

    // Personal information
    private String citizenId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Gender gender;
    private LocalDate dateOfBirth;
    private UserRole role;
}
