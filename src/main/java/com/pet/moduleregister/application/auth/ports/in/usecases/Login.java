package com.pet.moduleregister.application.auth.ports.in.usecases;

import com.pet.moduleregister.application.auth.dto.LoginDataDTO;
import com.pet.moduleregister.application.auth.dto.LoginResultDTO;

public interface Login {
    LoginResultDTO login(LoginDataDTO loginData);
}
