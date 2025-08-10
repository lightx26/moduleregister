package com.pet.moduleregister.application.user.ports.in.query;

import com.pet.moduleregister.entities.user.User;

import java.util.Optional;

public interface GetUserQuery {
    Optional<User> getUserById(Long userId);
    Optional<User> getUserByCode(String userCode);
}
