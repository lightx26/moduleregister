package com.pet.moduleregister.application.auth.ports.in;

import com.pet.moduleregister.application.auth.ports.in.dto.LoginResultDTO;
import com.pet.moduleregister.domain.user.model.User;

public interface RefreshUsecase {
    LoginResultDTO refresh(String refreshToken);
}
