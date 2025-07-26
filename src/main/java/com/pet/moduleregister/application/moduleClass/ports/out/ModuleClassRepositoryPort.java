package com.pet.moduleregister.application.moduleClass.ports.out;

import com.pet.moduleregister.application.moduleClass.dto.OpeningClass;
import com.pet.moduleregister.domain.moduleClass.ModuleClass;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface ModuleClassRepositoryPort {
    List<ModuleClass> findBySemesterId(Long semesterId);
    List<OpeningClass> findOpeningClassesBySemesterId(Long semesterId, int limit);
}
