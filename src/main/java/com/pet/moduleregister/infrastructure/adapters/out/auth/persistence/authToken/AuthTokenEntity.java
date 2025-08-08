package com.pet.moduleregister.infrastructure.adapters.out.auth.persistence.authToken;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "auth_token")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_token_id")
    private Long authTokenId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}
