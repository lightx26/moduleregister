package com.pet.moduleregister.application.auth.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class AuthTokenDTO {
    private String authTokenId;
    private Long userId;
    private String refreshToken;
    private Instant createdAt;
    private Instant updatedAt;
}
