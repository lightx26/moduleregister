package com.pet.moduleregister.application.auth.ports.in;

import com.pet.moduleregister.application.auth.ports.in.dto.LoginDataDTO;
import com.pet.moduleregister.application.auth.ports.in.dto.LoginResultDTO;

public interface LoginUsecase {
    LoginResultDTO login(LoginDataDTO loginData);
}
