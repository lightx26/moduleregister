package com.pet.moduleregister.infrastructure.adapters.user.in.web;

import com.pet.moduleregister.infrastructure.adapters.shared.in.web.exceptions.UnauthorizedException;
import com.pet.moduleregister.infrastructure.adapters.shared.in.web.security.AuthUtils;
import com.pet.moduleregister.infrastructure.adapters.user.in.web.dto.response.CurrentUserResponse;
import com.pet.moduleregister.entities.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserFacadeImpl implements UserFacade {
    @Override
    public CurrentUserResponse getCurrentUser() {
        User currentUser = AuthUtils.getCurrentUser().orElseThrow(
                () -> new UnauthorizedException("User not authenticated")
        );
        return CurrentUserResponse.builder()
                .userId(currentUser.getUserId())
                .userCode(currentUser.getUserCode())
                .citizenId(currentUser.getCitizenId())
                .email(currentUser.getEmail())
                .phoneNumber(currentUser.getPhoneNumber())
                .firstName(currentUser.getFirstName())
                .lastName(currentUser.getLastName())
                .gender(currentUser.getGender())
                .dateOfBirth(currentUser.getDateOfBirth())
                .role(currentUser.getRole())
                .build();
    }
}
