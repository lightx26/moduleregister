package com.pet.moduleregister.application.auth.ports.out;

import com.pet.moduleregister.application.auth.dto.UserDTO;

import java.util.Optional;

public interface GetUserPort {
    Optional<UserDTO> getUserByCode(String userCode);
}
