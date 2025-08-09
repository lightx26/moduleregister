package com.pet.moduleregister.application.moduleClass.ports.in.query;

import com.pet.moduleregister.entities.moduleClass.ModuleClass;

import java.util.List;

public interface GetModuleClassesByUser {
    List<ModuleClass> getModuleClassesByUser(Long userId);
    List<ModuleClass> getModuleClassesByUser(Long userId, String semesterId);
}
