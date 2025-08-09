package com.pet.moduleregister.infrastructure.adapters.in.web.auth.facade;

import com.pet.moduleregister.infrastructure.adapters.in.web.auth.dto.request.LoginParams;
import com.pet.moduleregister.infrastructure.adapters.in.web._shared.security.AuthUtils;
import com.pet.moduleregister.application.auth.ports.in.usecases.Login;
import com.pet.moduleregister.application.auth.ports.in.usecases.Logout;
import com.pet.moduleregister.application.auth.ports.in.usecases.Refresh;
import com.pet.moduleregister.application.auth.dto.LoginDataDTO;
import com.pet.moduleregister.application.auth.dto.LoginResultDTO;
import com.pet.moduleregister.application.auth.dto.LogoutDataDTO;
import com.pet.moduleregister.entities.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthFacadeImpl implements AuthFacade {
    private final Login loginUsecase;
    private final Refresh refreshUsecase;
    private final Logout logoutUsecase;

    @Override
    public LoginResultDTO login(LoginParams loginParams) {
        LoginDataDTO loginData = new LoginDataDTO(
            loginParams.getUserCode(),
            loginParams.getPassword()
        );

        return loginUsecase.login(loginData);
    }

    @Override
    public LoginResultDTO refresh(String refreshToken) {
        return refreshUsecase.refresh(refreshToken);
    }

    @Override
    public void logout(String accessToken, String refreshToken) {
        User currentUser = AuthUtils.getCurrentUser().orElseThrow(
            () -> new IllegalArgumentException("User not authenticated")
        );

        logoutUsecase.logout(currentUser, new LogoutDataDTO(
            accessToken,
            refreshToken
        ));
    }
}
