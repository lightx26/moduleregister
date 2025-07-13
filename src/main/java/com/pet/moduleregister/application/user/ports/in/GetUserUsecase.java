package com.pet.moduleregister.application.user.ports.in;

import com.pet.moduleregister.domain.user.model.User;

import java.util.List;

public interface GetUserUsecase {
    List<User> getAllUsers();
    User getUserById(String userId);
}
