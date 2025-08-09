package com.pet.moduleregister.infrastructure.adapters.in.web.auth.facade;

import com.pet.moduleregister.infrastructure.adapters.in.web.auth.dto.request.LoginParams;
import com.pet.moduleregister.application.auth.dto.LoginResultDTO;

public interface AuthFacade {
    LoginResultDTO login(LoginParams loginParams);
    LoginResultDTO refresh(String refreshToken);
    void logout(String accessToken, String refreshToken);
}
