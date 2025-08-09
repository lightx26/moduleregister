package com.pet.moduleregister.application.moduleClassStudent.ports.in.usecases;


public interface RegisterModuleClass {
    void registerModuleClass(String studentCode, String classCode);
    void registerModuleClasses(String[] classCode);
}
