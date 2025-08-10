package com.pet.moduleregister.infrastructure.adapters.out.auth;

import com.pet.moduleregister.application.auth.dto.UserDTO;
import com.pet.moduleregister.application.auth.ports.out.GetUserPort;
import com.pet.moduleregister.application.user.ports.in.query.GetUserQuery;
import com.pet.moduleregister.entities.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetUserAdapter implements GetUserPort {
    private final GetUserQuery getUserQuery;

    @Override
    public Optional<UserDTO> getUserByCode(String userCode) {
        Optional<User> userOpt = getUserQuery.getUserByCode(userCode);
        return userOpt.map(user -> new UserDTO(
                user.getUserId(),
                user.getUserCode(),
                user.getPassword()
        ));
    }
}
