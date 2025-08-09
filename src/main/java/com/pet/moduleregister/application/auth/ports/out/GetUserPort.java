package com.pet.moduleregister.application.auth.ports.out;

import com.pet.moduleregister.application.auth.dto.UserDTO;

public interface GetUserPort {
    UserDTO getUserByCode(String userCode);
}
