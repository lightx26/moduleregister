package com.pet.moduleregister.infrastructure.adapters.user.in.web.dto.response;

import com.pet.moduleregister.entities.user.enums.Gender;
import com.pet.moduleregister.entities.user.enums.UserRole;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class CurrentUserResponse {
    private Long userId;
    private String userCode;
    private String citizenId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Gender gender;
    private LocalDate dateOfBirth;
    private UserRole role;
}
