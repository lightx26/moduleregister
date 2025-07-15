package com.pet.moduleregister.adapters.in.web.auth.facade;

import com.pet.moduleregister.adapters.in.web.auth.dto.request.LoginParams;
import com.pet.moduleregister.adapters.in.web.auth.dto.request.RefreshParams;
import com.pet.moduleregister.application.auth.ports.in.dto.LoginResultDTO;

public interface AuthFacade {
    LoginResultDTO login(LoginParams loginParams);
    LoginResultDTO refresh(RefreshParams refreshParams);
    void logout(String accessToken, String refreshToken);
}
