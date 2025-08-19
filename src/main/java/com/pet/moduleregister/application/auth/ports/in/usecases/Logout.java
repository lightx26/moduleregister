package com.pet.moduleregister.application.auth.ports.in.usecases;

import com.pet.moduleregister.application._shared.AuthUser;
import com.pet.moduleregister.application.auth.dto.LogoutDataDTO;

public interface Logout {
    void logout(AuthUser currentUser, LogoutDataDTO logoutData);
}
