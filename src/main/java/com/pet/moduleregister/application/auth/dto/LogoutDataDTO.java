package com.pet.moduleregister.application.auth.dto;

public record LogoutDataDTO (
    String accessToken,
    String refreshToken
) {}
