package com.pet.moduleregister.application.user.ports.in.query;

import com.pet.moduleregister.entities.user.User;

import java.util.List;

public interface GetUser {
    User getUserById(Long userId);
    User getUserByCode(String userCode);
}
