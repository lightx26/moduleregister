package com.pet.moduleregister.application.auth.ports.in;

import com.pet.moduleregister.application.auth.ports.in.dto.LoginResultDTO;

public interface RefreshUsecase {
    LoginResultDTO refresh(String refreshToken);
}
