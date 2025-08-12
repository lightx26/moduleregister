package com.pet.moduleregister.application.module.ports.out;

import com.pet.moduleregister.application.module.dto.ModuleBasicDTO;

import java.util.List;

public interface ModuleRepositoryPort {
    List<ModuleBasicDTO> getModulesOfProgram(Long programId);
}
