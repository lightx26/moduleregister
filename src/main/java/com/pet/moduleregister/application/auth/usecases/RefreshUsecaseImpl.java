package com.pet.moduleregister.application.auth.usecases;

import com.pet.moduleregister.application.auth.ports.in.RefreshUsecase;
import com.pet.moduleregister.application.auth.ports.in.dto.LoginResultDTO;
import com.pet.moduleregister.application.auth.ports.out.AuthTokenRepositoryPort;
import com.pet.moduleregister.application.auth.ports.out.TokenServicePort;
import com.pet.moduleregister.application.auth.ports.out.dto.AuthTokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RefreshUsecaseImpl implements RefreshUsecase {
    private final AuthTokenRepositoryPort authTokenRepositoryPort;
    private final TokenServicePort tokenServicePort;
    @Override
    @Transactional
    public LoginResultDTO refresh(String refreshToken) {
        if (!tokenServicePort.isValidToken(refreshToken)) {
            throw new IllegalArgumentException("Invalid refresh token");
        }

        String userId = tokenServicePort.extractUserId(refreshToken);

        AuthTokenDTO authToken = authTokenRepositoryPort.findByUserIdAndToken(userId, refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Refresh token not found"));

        // Generate new access and refresh tokens
        String newAccessToken = tokenServicePort.generateAccessToken(userId);
        String newRefreshToken = tokenServicePort.generateRefreshToken(userId);
        // Update the auth token in database
        authTokenRepositoryPort.update(authToken.getAuthTokenId(), newRefreshToken, tokenServicePort.extractIssuedAt(newRefreshToken));

        return new LoginResultDTO(newAccessToken, newRefreshToken);
    }
}
