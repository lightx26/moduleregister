package com.pet.moduleregister.application.moduleClass.services;

import com.pet.moduleregister.application.moduleClass.ports.in.query.GetModuleClass;
import com.pet.moduleregister.application.moduleClass.ports.out.ModuleClassRepositoryPort;
import com.pet.moduleregister.entities.moduleClass.ModuleClass;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetModuleClassImpl implements GetModuleClass {
    private final ModuleClassRepositoryPort moduleClassRepository;
    @Override
    public Optional<ModuleClass> getByClassCode(String classCode) {
        return moduleClassRepository.findByCode(classCode);
    }
}
