package com.pet.moduleregister.infrastructure.adapters.out.moduleClassStudent.persistence;

import com.pet.moduleregister.application.moduleClassStudent.ports.out.ModuleClassStudentRepositoryPort;
import com.pet.moduleregister.entities.moduleClassStudent.ModuleClassStudent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ModuleClassStudentRepositoryAdapter implements ModuleClassStudentRepositoryPort {
    private final ModuleClassStudentJpaRepository moduleClassStudentJpaRepository;

    @Override
    public void add(ModuleClassStudent moduleClassStudent) {
        ModuleClassStudentEntity entity = new ModuleClassStudentEntity(
                null,
                moduleClassStudent.getModuleClassId(),
                moduleClassStudent.getStudentId(),
                moduleClassStudent.getStatus(),
                moduleClassStudent.getRetakeCount(),
                moduleClassStudent.getCreatedAt(),
                moduleClassStudent.getUpdatedAt()
        );
        moduleClassStudentJpaRepository.save(entity);
    }
}
