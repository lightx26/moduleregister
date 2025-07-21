package com.pet.moduleregister.application.auth.usecases;

import com.pet.moduleregister.application.auth.ports.in.LoginUsecase;
import com.pet.moduleregister.application.auth.ports.in.dto.LoginDataDTO;
import com.pet.moduleregister.application.auth.ports.in.dto.LoginResultDTO;
import com.pet.moduleregister.application.auth.ports.out.AuthTokenRepositoryPort;
import com.pet.moduleregister.application.auth.ports.out.TokenServicePort;
import com.pet.moduleregister.application.auth.ports.out.dto.AuthTokenDTO;
import com.pet.moduleregister.application.shared.exceptions.NotFoundException;
import com.pet.moduleregister.application.user.ports.in.GetUserUsecase;
import com.pet.moduleregister.application.user.ports.out.UserRepositoryPort;
import com.pet.moduleregister.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class LoginUsecaseImpl implements LoginUsecase {

    private final GetUserUsecase getUserUsecase;
    private final AuthTokenRepositoryPort authTokenRepositoryPort;
    private final TokenServicePort tokenServicePort;
    private final PasswordEncoder passwordEncoder;
    @Override
    @Transactional
    public LoginResultDTO login(LoginDataDTO loginData) {
        String userCode = loginData.userCode();
        String password = loginData.password();

        User user;
        try {
            user = getUserUsecase.getUserByCode(userCode);
        } catch (NotFoundException ignored) {
            user = null;
        }

        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid userCode or password");
        }

        String accessToken = tokenServicePort.generateAccessToken(user.getUserId().toString());
        String refreshToken = tokenServicePort.generateRefreshToken(user.getUserId().toString());

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
