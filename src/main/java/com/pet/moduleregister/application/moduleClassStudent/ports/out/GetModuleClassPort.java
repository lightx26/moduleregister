package com.pet.moduleregister.application.moduleClassStudent.ports.out;

import com.pet.moduleregister.application.moduleClassStudent.dto.ModuleClassDTO;

import java.util.Optional;

public interface GetModuleClassPort {
    Optional<ModuleClassDTO> getModuleClassByCode(String classCode);
}
