package com.pet.moduleregister.application.auth.ports.in.usecases;

import com.pet.moduleregister.application.auth.dto.LogoutDataDTO;
import com.pet.moduleregister.entities.user.User;

public interface Logout {
    void logout(User currentUser, LogoutDataDTO logoutData);
}
