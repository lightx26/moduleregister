package com.pet.moduleregister.adapters.out.persistence.user;

import com.pet.moduleregister.domain.user.enums.AccountStatus;
import com.pet.moduleregister.domain.user.enums.Gender;
import com.pet.moduleregister.domain.user.enums.UserRole;
import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_code", unique = true)
    private String userCode;

    @Column(name = "password")
    private String password;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
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
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
