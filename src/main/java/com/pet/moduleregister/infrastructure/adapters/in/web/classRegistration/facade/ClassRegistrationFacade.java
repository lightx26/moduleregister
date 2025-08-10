package com.pet.moduleregister.infrastructure.adapters.in.web.classRegistration.facade;

import com.pet.moduleregister.infrastructure.adapters.in.web.classRegistration.dto.request.ClassRegistrationParams;
import com.pet.moduleregister.infrastructure.adapters.in.web.classRegistration.dto.response.RegisterSuccessResponse;

public interface ClassRegistrationFacade {
    RegisterSuccessResponse registerClass(ClassRegistrationParams params);
}
