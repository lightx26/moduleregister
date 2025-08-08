package com.pet.moduleregister.infrastructure.adapters.in.web.user.facade;

import com.pet.moduleregister.infrastructure.adapters.in.web.user.dto.response.CurrentUserResponse;

public interface UserFacade {
    CurrentUserResponse getCurrentUser();
}
