package com.pet.moduleregister.application.auth.ports.in.dto;

public record LogoutDataDTO (
    String accessToken,
    String refreshToken
) {}
