package com.pet.moduleregister.application.auth.ports.out;

public interface TokenServicePort {
    String generateAccessToken(String userId);
    String generateRefreshToken(String userId);
    String extractUserId(String token);
    boolean isValidToken(String token);
}
