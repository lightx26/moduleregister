package com.pet.moduleregister.infrastructure.adapters.out.moduleClassStudent;

import com.pet.moduleregister.application.moduleClass.ports.in.query.GetModuleClassQuery;
import com.pet.moduleregister.application.moduleClassStudent.dto.ModuleClassDTO;
import com.pet.moduleregister.application.moduleClassStudent.ports.out.GetModuleClassPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetModuleClassAdapter implements GetModuleClassPort {
    private final GetModuleClassQuery getModuleClass;

    @Override
    public Optional<ModuleClassDTO> getModuleClassByCode(String classCode) {
        return getModuleClass.getByClassCode(classCode)
                .map(moduleClass -> new ModuleClassDTO(
                        moduleClass.getModuleClassId(),
                        moduleClass.getModuleClassCode(),
                        moduleClass.getSemesterId()
                ));
    }
}
