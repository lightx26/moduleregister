package com.pet.moduleregister.adapters.out.token;

import com.pet.moduleregister.application.user.ports.out.TokenGeneratorPort;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtGenerator implements TokenGeneratorPort {
    @Value("${moduleregister.jwt.secret.key}")
    private String secret;
    @Value("${moduleregister.jwt.expiry.access-token}")
    private long accessTokenExpirySeconds;
    @Value("${moduleregister.jwt.expiry.refresh-token}")
    private long refreshTokenExpirySeconds;

    private Key secretKey;

    @PostConstruct
    private void init() {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    }
    private String generateToken(String subject, long expirySeconds) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(subject)
                .issuedAt(Date.from(now))
                .issuer("moduleregister")
                .expiration(Date.from(now.plusSeconds(expirySeconds)))
                .signWith(secretKey)
                .compact();
    }

    @Override
    public String generateAccessToken(String userId) {
        return generateToken(userId, accessTokenExpirySeconds); // 15 minutes
    }

    @Override
    public String generateRefreshToken(String userId) {
        return generateToken(userId, refreshTokenExpirySeconds); // 30 days
    }
}
