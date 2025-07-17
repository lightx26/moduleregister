package com.pet.moduleregister.adapters.in.web.user.facade;

import com.pet.moduleregister.adapters.in.web.shared.exceptions.UnauthorizedException;
import com.pet.moduleregister.adapters.in.web.shared.security.AuthUtils;
import com.pet.moduleregister.adapters.in.web.user.dto.response.CurrentUserResponse;
import com.pet.moduleregister.domain.user.User;
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
