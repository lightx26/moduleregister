package com.pet.moduleregister.infrastructure.adapters.in.web.classRegistration.facade;

import com.pet.moduleregister.application._shared.AuthUser;
import com.pet.moduleregister.application.moduleClassStudent.dto.RegisteredModuleClass;
import com.pet.moduleregister.application.moduleClassStudent.ports.in.usecases.RegisterModuleClass;
import com.pet.moduleregister.infrastructure.adapters.in.web._shared.security.AuthUtils;
import com.pet.moduleregister.infrastructure.adapters.in.web.classRegistration.dto.request.ClassRegistrationParams;
import com.pet.moduleregister.infrastructure.adapters.in.web.classRegistration.dto.response.RegisterSuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClassRegistrationFacadeImpl implements ClassRegistrationFacade {
    private final RegisterModuleClass registerModuleClass;

    @Override
    public RegisterSuccessResponse registerClass(ClassRegistrationParams params) {
        AuthUser authUser = AuthUtils.getCurrentUser().map(
                user -> new AuthUser(
                        user.getUserId(),
                        user.getUserCode(),
                        user.getStatus(),
                        user.getRole()
                )
        ).orElseThrow(() -> new IllegalStateException("User not authenticated"));
        // Implement the logic to register a class using the provided parameters
        // This is just a placeholder method
        RegisteredModuleClass registeredModuleClass = registerModuleClass.registerModuleClass(authUser, params.getModuleClassCode());
        return new RegisterSuccessResponse(
                registeredModuleClass.getModuleClassCode(),
                registeredModuleClass.getRegisteredAt()
        );
    }
}
