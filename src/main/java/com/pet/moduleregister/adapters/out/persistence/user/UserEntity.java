package com.pet.moduleregister.adapters.out.persistence.user;

import com.pet.moduleregister.domain.modules.user.enums.AccountStatus;
import com.pet.moduleregister.domain.modules.user.enums.Gender;
import com.pet.moduleregister.domain.modules.user.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "`user`")
@Getter
@Setter
public class UserEntity {
    // Account information
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "password")
    private String password;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "status")
    private AccountStatus status;

    // Personal information
    @Column(name = "citizen_id")
    private String citizenId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "role")
    private UserRole role;
}
