package com.pet.moduleregister.adapters.in.web.auth.facade;

import com.pet.moduleregister.adapters.in.web.auth.dto.request.LoginParams;
import com.pet.moduleregister.application.user.ports.in.LoginUsecase;
import com.pet.moduleregister.application.user.ports.in.dto.LoginDataDTO;
import com.pet.moduleregister.application.user.ports.in.dto.LoginResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthFacadeImpl implements AuthFacade {
    private final LoginUsecase loginUsecase;

    @Override
    public LoginResultDTO login(LoginParams loginParams) {
        LoginDataDTO loginData = new LoginDataDTO(
            loginParams.getUserId(),
            loginParams.getPassword()
        );

        return loginUsecase.login(loginData);
    }
}
