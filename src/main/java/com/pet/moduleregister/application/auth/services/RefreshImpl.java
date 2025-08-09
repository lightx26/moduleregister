package com.pet.moduleregister.application.auth.services;

import com.pet.moduleregister.application.auth.ports.in.usecases.Refresh;
import com.pet.moduleregister.application.auth.dto.LoginResultDTO;
import com.pet.moduleregister.application.auth.ports.out.AuthTokenRepositoryPort;
import com.pet.moduleregister.application.auth.ports.out.TokenServicePort;
import com.pet.moduleregister.application.auth.dto.AuthTokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RefreshImpl implements Refresh {
    private final AuthTokenRepositoryPort authTokenRepositoryPort;
    private final TokenServicePort tokenServicePort;
    @Override
    @Transactional
    public LoginResultDTO refresh(String refreshToken) {
        if (!tokenServicePort.isValidToken(refreshToken)) {
            throw new IllegalArgumentException("Invalid refresh token");
        }

        Long userId = Long.parseLong(tokenServicePort.extractUserId(refreshToken));

        AuthTokenDTO authToken = authTokenRepositoryPort.findByUserIdAndToken(userId, refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Refresh token not found"));

        // Generate new access and refresh tokens
        String newAccessToken = tokenServicePort.generateAccessToken(userId.toString());
        String newRefreshToken = tokenServicePort.generateRefreshToken(userId.toString());
        // Update the auth token in database
        authTokenRepositoryPort.update(authToken.getAuthTokenId(), newRefreshToken, tokenServicePort.extractIssuedAt(newRefreshToken));

        return new LoginResultDTO(newAccessToken, newRefreshToken);
    }
}
