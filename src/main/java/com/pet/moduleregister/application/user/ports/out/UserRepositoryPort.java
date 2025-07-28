package com.pet.moduleregister.application.user.ports.out;

import com.pet.moduleregister.entities.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {
    Optional<User> findById(Long userId);
    Optional<User> findByCode(String userCode);
    List<User> findAll();
}
