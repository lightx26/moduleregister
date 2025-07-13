package com.pet.moduleregister.application.user.ports.out;

public interface TokenGeneratorPort {
    String generateAccessToken(String userId);
    String generateRefreshToken(String userId);
}
