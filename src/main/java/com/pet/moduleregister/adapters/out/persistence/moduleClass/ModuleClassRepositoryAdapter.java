package com.pet.moduleregister.adapters.out.persistence.moduleClass;

import com.pet.moduleregister.adapters.out.persistence.moduleClass.mappers.ModuleClassDomainMapper;
import com.pet.moduleregister.application.moduleClass.ports.out.ModuleClassRepositoryPort;
import com.pet.moduleregister.domain.moduleClass.ModuleClass;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ModuleClassRepositoryAdapter implements ModuleClassRepositoryPort {
    private final ModuleClassJpaRepository moduleClassJpaRepository;
    private final ModuleClassDomainMapper moduleClassDomainMapper;

    @Override
    public List<ModuleClass> findBySemesterId(Long semesterId) {
        return moduleClassJpaRepository.findBySemesterId(semesterId)
                .stream()
                .map(moduleClassDomainMapper::toDomain)
                .toList();
    }
}
