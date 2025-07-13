package com.pet.moduleregister.adapters.in.web.auth.facade;

import com.pet.moduleregister.adapters.in.web.auth.dto.request.LoginParams;
import com.pet.moduleregister.application.user.ports.in.dto.LoginResultDTO;

public interface AuthFacade {
    LoginResultDTO login(LoginParams loginParams);
}
