package com.pet.moduleregister.application.moduleClass.ports.out;

import com.pet.moduleregister.application.moduleClass.dto.usecases.OpeningClass;
import com.pet.moduleregister.application.moduleClass.dto.usecases.OpeningClassDetails;
import com.pet.moduleregister.entities.moduleClass.ModuleClass;

import java.util.List;
import java.util.Optional;

public interface ModuleClassRepositoryPort {
    Optional<ModuleClass> findById(Long moduleClassId);
    boolean existsById(Long moduleClassId);
    Optional<ModuleClass> findByCode(String classCode);
    boolean existsByCode(String classCode);
    List<ModuleClass> findBySemesterId(Long semesterId);
    List<OpeningClass> findOpeningClassesBySemesterId(Long semesterId, int limit);
    Optional<OpeningClassDetails> findOpeningModuleClassByCode(String classCode);
}
