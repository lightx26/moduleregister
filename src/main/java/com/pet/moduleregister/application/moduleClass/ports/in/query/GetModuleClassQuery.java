package com.pet.moduleregister.application.moduleClass.ports.in.query;

import com.pet.moduleregister.entities.moduleClass.ModuleClass;

import java.util.Optional;

public interface GetModuleClassQuery {
    Optional<ModuleClass> getByClassCode(String classCode);
}
