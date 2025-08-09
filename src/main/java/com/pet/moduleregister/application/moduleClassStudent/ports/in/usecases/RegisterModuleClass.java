package com.pet.moduleregister.application.moduleClassStudent.ports.in.usecases;


import com.pet.moduleregister.application._shared.AuthUser;

public interface RegisterModuleClass {
    void registerModuleClass(AuthUser currentStudent, String classCode);
    void registerModuleClasses(String[] classCode);
}
