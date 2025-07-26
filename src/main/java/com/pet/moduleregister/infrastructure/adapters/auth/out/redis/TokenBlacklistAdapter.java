package com.pet.moduleregister.infrastructure.adapters.auth.out.redis;

import com.pet.moduleregister.application.auth.ports.out.TokenBlackListPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
@RequiredArgsConstructor
public class TokenBlacklistAdapter implements TokenBlackListPort {
    private final RedisTemplate<String, String> redisTemplate;

    private static final String PREFIX = "token-blacklist:";

    @Override
    public void blacklistToken(String token, Instant expiration) {
        String key = PREFIX + token;
        long ttlSeconds = expiration.getEpochSecond() - Instant.now().getEpochSecond();
        if (ttlSeconds > 0) {
            redisTemplate.opsForValue().set(key, "1", Duration.ofSeconds(ttlSeconds));
        }
    }

    @Override
    public boolean isTokenBlacklisted(String token) {
        String key = PREFIX + token;
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }
}
