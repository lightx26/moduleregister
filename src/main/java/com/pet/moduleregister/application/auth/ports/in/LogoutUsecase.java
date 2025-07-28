package com.pet.moduleregister.application.auth.ports.in;

import com.pet.moduleregister.application.auth.ports.in.dto.LogoutDataDTO;
import com.pet.moduleregister.entities.user.User;

public interface LogoutUsecase {
    void logout(User currentUser, LogoutDataDTO logoutData);
}
