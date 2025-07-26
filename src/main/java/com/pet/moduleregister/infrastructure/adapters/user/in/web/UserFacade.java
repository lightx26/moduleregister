package com.pet.moduleregister.infrastructure.adapters.user.in.web;

import com.pet.moduleregister.infrastructure.adapters.user.in.web.dto.response.CurrentUserResponse;

public interface UserFacade {
    CurrentUserResponse getCurrentUser();
}
