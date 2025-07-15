package com.pet.moduleregister.application.auth.ports.in;

import com.pet.moduleregister.application.auth.ports.in.dto.LogoutDataDTO;
import com.pet.moduleregister.domain.user.model.User;

public interface LogoutUsecase {
    void logout(User currentUser, LogoutDataDTO logoutData);
}
