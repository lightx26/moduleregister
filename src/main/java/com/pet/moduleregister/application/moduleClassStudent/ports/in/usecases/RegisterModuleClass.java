package com.pet.moduleregister.application.moduleClassStudent.ports.in.usecases;


import com.pet.moduleregister.application._shared.AuthUser;
import com.pet.moduleregister.application.moduleClassStudent.dto.RegisteredModuleClass;

public interface RegisterModuleClass {
    RegisteredModuleClass registerModuleClass(AuthUser currentStudent, String classCode);
    void registerModuleClasses(String[] classCode);
}
