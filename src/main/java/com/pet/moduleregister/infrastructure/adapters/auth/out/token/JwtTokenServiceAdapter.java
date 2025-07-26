package com.pet.moduleregister.infrastructure.adapters.auth.out.token;

import com.pet.moduleregister.application.auth.ports.out.TokenServicePort;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenServiceAdapter implements TokenServicePort {
    @Value("${moduleregister.jwt.expiry.access-token}")
    private long accessTokenExpirySeconds;
    @Value("${moduleregister.jwt.expiry.refresh-token}")
    private long refreshTokenExpirySeconds;

    private final SecretKey secretKey;
    private final JwtDecoder jwtDecoder;

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

    @Override
    public String extractUserId(String token) {
        return jwtDecoder.decode(token).getSubject();
    }

    @Override
    public Instant extractIssuedAt(String token) {
        return jwtDecoder.decode(token).getIssuedAt();
    }

    @Override
    public Instant extractExpiration(String token) {
        return jwtDecoder.decode(token).getExpiresAt();
    }

    @Override
    public boolean isValidToken(String token) {
        try {
            jwtDecoder.decode(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
