package com.pet.moduleregister.application.module.ports.in.query;

import com.pet.moduleregister.application.module.dto.ModuleBasicDTO;

import java.util.List;

public interface GetModulesOfProgramQuery {
    List<ModuleBasicDTO> getModulesOfProgram(Long programId);
}
