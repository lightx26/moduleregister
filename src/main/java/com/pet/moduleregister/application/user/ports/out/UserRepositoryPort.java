package com.pet.moduleregister.application.user.ports.out;

import com.pet.moduleregister.domain.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {
    Optional<User> findById(String userId);
    List<User> findAll();
}
