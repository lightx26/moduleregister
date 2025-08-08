package com.pet.moduleregister.infrastructure.adapters.in.web.auth.facade;

import com.pet.moduleregister.infrastructure.adapters.in.web.auth.dto.request.LoginParams;
import com.pet.moduleregister.infrastructure.adapters.in.web._shared.security.AuthUtils;
import com.pet.moduleregister.application.auth.ports.in.LoginUsecase;
import com.pet.moduleregister.application.auth.ports.in.LogoutUsecase;
import com.pet.moduleregister.application.auth.ports.in.RefreshUsecase;
import com.pet.moduleregister.application.auth.ports.in.dto.LoginDataDTO;
import com.pet.moduleregister.application.auth.ports.in.dto.LoginResultDTO;
import com.pet.moduleregister.application.auth.ports.in.dto.LogoutDataDTO;
import com.pet.moduleregister.entities.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthFacadeImpl implements AuthFacade {
    private final LoginUsecase loginUsecase;
    private final RefreshUsecase refreshUsecase;
    private final LogoutUsecase logoutUsecase;

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
