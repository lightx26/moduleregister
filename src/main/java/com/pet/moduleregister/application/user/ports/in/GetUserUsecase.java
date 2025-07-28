package com.pet.moduleregister.application.user.ports.in;

import com.pet.moduleregister.entities.user.User;

import java.util.List;

public interface GetUserUsecase {
    List<User> getAllUsers();
    User getUserById(Long userId);
    User getUserByCode(String userCode);
}
