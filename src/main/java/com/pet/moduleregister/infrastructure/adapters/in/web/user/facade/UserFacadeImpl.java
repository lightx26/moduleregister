package com.pet.moduleregister.infrastructure.adapters.in.web.user.facade;

import com.pet.moduleregister.infrastructure.adapters.in.web._shared.exceptions.UnauthorizedException;
import com.pet.moduleregister.infrastructure.adapters.in.web._shared.security.AuthUser;
import com.pet.moduleregister.infrastructure.adapters.in.web._shared.security.AuthUtils;
import com.pet.moduleregister.infrastructure.adapters.in.web.user.dto.response.CurrentUserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserFacadeImpl implements UserFacade {
    @Override
    public CurrentUserResponse getCurrentUser() {
        AuthUser currentUser = AuthUtils.getCurrentUser().orElseThrow(
                () -> new UnauthorizedException("User not authenticated")
        );
        return CurrentUserResponse.builder()
                .userId(currentUser.getUserId().toString())
                .userCode(currentUser.getUserCode())
                .email(currentUser.getEmail())
                .firstName(currentUser.getFirstName())
                .lastName(currentUser.getLastName())
                .role(currentUser.getRole())
                .build();
    }
}
