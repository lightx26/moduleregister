package com.pet.moduleregister.application.moduleClass.ports.out;

import com.pet.moduleregister.domain.moduleClass.ModuleClass;

import java.util.List;

public interface ModuleClassRepositoryPort {
    List<ModuleClass> findBySemesterId(Long semesterId);
}
