package com.pet.moduleregister.application.auth.ports.out;

import java.time.Instant;

public interface TokenBlackListPort {
    void blacklistToken(String token, Instant expiration);
    boolean isTokenBlacklisted(String token);
}
