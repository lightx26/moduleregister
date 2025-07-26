package com.pet.moduleregister.infrastructure.adapters.auth.in.web.facade;

import com.pet.moduleregister.infrastructure.adapters.auth.in.web.dto.request.LoginParams;
import com.pet.moduleregister.infrastructure.adapters.auth.in.web.dto.request.RefreshParams;
import com.pet.moduleregister.application.auth.ports.in.dto.LoginResultDTO;

public interface AuthFacade {
    LoginResultDTO login(LoginParams loginParams);
    LoginResultDTO refresh(String refreshToken);
    void logout(String accessToken, String refreshToken);
}
