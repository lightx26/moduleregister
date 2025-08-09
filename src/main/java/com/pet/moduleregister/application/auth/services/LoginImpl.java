package com.pet.moduleregister.application.auth.services;

import com.pet.moduleregister.application.auth.dto.UserDTO;
import com.pet.moduleregister.application.auth.ports.in.usecases.Login;
import com.pet.moduleregister.application.auth.dto.LoginDataDTO;
import com.pet.moduleregister.application.auth.dto.LoginResultDTO;
import com.pet.moduleregister.application.auth.ports.out.AuthTokenRepositoryPort;
import com.pet.moduleregister.application.auth.ports.out.GetUserPort;
import com.pet.moduleregister.application.auth.ports.out.TokenServicePort;
import com.pet.moduleregister.application.auth.dto.AuthTokenDTO;
import com.pet.moduleregister.application._shared.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class LoginImpl implements Login {
    private final GetUserPort getUser;
    private final AuthTokenRepositoryPort authTokenRepositoryPort;
    private final TokenServicePort tokenServicePort;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public LoginResultDTO login(LoginDataDTO loginData) {
        String userCode = loginData.userCode();
        String password = loginData.password();

        UserDTO user;
        try {
            user = getUser.getUserByCode(userCode);
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
