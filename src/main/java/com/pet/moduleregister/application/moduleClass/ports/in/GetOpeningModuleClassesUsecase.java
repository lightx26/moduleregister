package com.pet.moduleregister.application.moduleClass.ports.in;

import com.pet.moduleregister.domain.moduleClass.ModuleClass;

import java.util.List;

public interface GetOpeningModuleClassesUsecase {
    List<ModuleClass> getOpeningModuleClasses();
}
