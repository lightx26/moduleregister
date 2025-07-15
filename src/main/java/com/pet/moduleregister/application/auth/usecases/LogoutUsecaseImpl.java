package com.pet.moduleregister.application.auth.usecases;

import com.pet.moduleregister.application.auth.ports.in.LogoutUsecase;
import com.pet.moduleregister.application.auth.ports.in.dto.LogoutDataDTO;
import com.pet.moduleregister.application.auth.ports.out.AuthTokenRepositoryPort;
import com.pet.moduleregister.application.auth.ports.out.TokenBlackListPort;
import com.pet.moduleregister.application.auth.ports.out.TokenServicePort;
import com.pet.moduleregister.application.auth.ports.out.dto.AuthTokenDTO;
import com.pet.moduleregister.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LogoutUsecaseImpl implements LogoutUsecase {
    private final AuthTokenRepositoryPort authTokenRepositoryPort;
    private final TokenServicePort tokenServicePort;
    private final TokenBlackListPort tokenBlackListPort;

    @Override
    @Transactional
    public void logout(User currentUser, LogoutDataDTO logoutData) {
        String accessToken = logoutData.accessToken();
        String refreshToken = logoutData.refreshToken();

        // Remove the refresh token from the database
        if (refreshToken != null && tokenServicePort.isValidToken(refreshToken)) {
            Optional<AuthTokenDTO> authTokenOpt = authTokenRepositoryPort.findByUserIdAndToken(
                            currentUser.getUserId(), logoutData.refreshToken());

            authTokenOpt.ifPresent(authTokenDTO -> authTokenRepositoryPort.deleteById(authTokenDTO.getAuthTokenId()));
        }

        // Blacklist the access token
        tokenBlackListPort.blacklistToken(accessToken, tokenServicePort.extractExpiration(accessToken));
    }
}
