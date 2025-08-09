package com.pet.moduleregister.application.auth.ports.in.usecases;

import com.pet.moduleregister.application.auth.dto.LoginResultDTO;

public interface Refresh {
    LoginResultDTO refresh(String refreshToken);
}
