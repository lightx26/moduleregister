package com.pet.moduleregister.application.auth.usecases;

import com.pet.moduleregister.application.auth.ports.in.LoginUsecase;
import com.pet.moduleregister.application.auth.ports.in.dto.LoginDataDTO;
import com.pet.moduleregister.application.auth.ports.in.dto.LoginResultDTO;
import com.pet.moduleregister.application.auth.ports.out.AuthTokenRepositoryPort;
import com.pet.moduleregister.application.auth.ports.out.TokenServicePort;
import com.pet.moduleregister.application.auth.ports.out.dto.AuthTokenDTO;
import com.pet.moduleregister.application.shared.exceptions.NotFoundException;
import com.pet.moduleregister.application.user.ports.out.UserRepositoryPort;
import com.pet.moduleregister.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class LoginUsecaseImpl implements LoginUsecase {

    private final UserRepositoryPort userRepositoryPort;
    private final AuthTokenRepositoryPort authTokenRepositoryPort;
    private final TokenServicePort tokenServicePort;
    private final PasswordEncoder passwordEncoder;
    @Override
    @Transactional
    public LoginResultDTO login(LoginDataDTO loginData) {
        String userId = loginData.userId();
        String password = loginData.password();

        User user = userRepositoryPort.findById(userId)
                .orElseThrow(() -> new NotFoundException("Invalid user ID or password"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid user ID or password");
        }

        String accessToken = tokenServicePort.generateAccessToken(user.getUserId());
        String refreshToken = tokenServicePort.generateRefreshToken(user.getUserId());

        // Store the refresh token in the database
        Instant tokenIssuedAt = tokenServicePort.extractIssuedAt(refreshToken);
        AuthTokenDTO authTokenDTO = AuthTokenDTO.builder()
                .userId(user.getUserId())
                .refreshToken(refreshToken)
                .createdAt(tokenIssuedAt)
                .updatedAt(tokenIssuedAt)
                .build();

        authTokenRepositoryPort.create(authTokenDTO);

        return new LoginResultDTO(accessToken, refreshToken);
    }
}
