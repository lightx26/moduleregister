package com.pet.moduleregister.infrastructure.adapters.out.auth;

import com.pet.moduleregister.application.auth.dto.UserDTO;
import com.pet.moduleregister.application.auth.ports.out.GetUserPort;
import com.pet.moduleregister.application.user.ports.in.query.GetUser;
import com.pet.moduleregister.entities.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetUserAdapter implements GetUserPort {
    private final GetUser getUser;

    @Override
    public UserDTO getUserByCode(String userCode) {
        User user = getUser.getUserByCode(userCode);
        return new UserDTO(
               user.getUserId(),
                user.getUserCode(),
                user.getPassword()
        );
    }
}
