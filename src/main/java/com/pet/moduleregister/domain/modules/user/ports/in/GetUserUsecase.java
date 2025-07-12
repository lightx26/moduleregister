package com.pet.moduleregister.domain.modules.user.ports.in;

import com.pet.moduleregister.domain.modules.user.model.User;

import java.util.List;

public interface GetUserUsecase {
    List<User> getAllUsers();
    User getUserById(String userId);
}
