package com.pet.moduleregister.application.auth.ports.out;

import java.time.Instant;

public interface TokenServicePort {
    String generateAccessToken(String userId);
    String generateRefreshToken(String userId);
    String extractUserId(String token);
    Instant extractIssuedAt(String token);
    Instant extractExpiration(String token);
    boolean isValidToken(String token);
}
