package com.pet.moduleregister.domain.modules.user.ports.out;

import com.pet.moduleregister.domain.modules.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {
    Optional<User> findById(String userId);
    List<User> findAll();
}
