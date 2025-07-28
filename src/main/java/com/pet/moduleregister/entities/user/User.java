package com.pet.moduleregister.entities.user;

import com.pet.moduleregister.entities.user.enums.AccountStatus;
import com.pet.moduleregister.entities.user.enums.Gender;
import com.pet.moduleregister.entities.user.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    // Account information
    private Long userId;
    private String userCode;
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
