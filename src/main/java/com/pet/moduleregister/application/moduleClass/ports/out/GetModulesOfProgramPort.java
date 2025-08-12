package com.pet.moduleregister.application.moduleClass.ports.out;

import com.pet.moduleregister.application.moduleClass.dto.usecases.ModuleInProgram;

import java.util.List;

public interface GetModulesOfProgramPort {
    List<ModuleInProgram> getModulesOfProgram(Long programId);
}
