package com.pet.moduleregister.application.user.ports.in;

import com.pet.moduleregister.application.user.ports.in.dto.LoginDataDTO;
import com.pet.moduleregister.application.user.ports.in.dto.LoginResultDTO;

public interface LoginUsecase {
    LoginResultDTO login(LoginDataDTO loginData);
}
