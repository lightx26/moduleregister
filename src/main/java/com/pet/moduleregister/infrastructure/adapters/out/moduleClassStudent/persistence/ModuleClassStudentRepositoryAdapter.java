package com.pet.moduleregister.infrastructure.adapters.out.moduleClassStudent.persistence;

import com.pet.moduleregister.application.moduleClassStudent.ports.out.ModuleClassStudentRepositoryPort;
import com.pet.moduleregister.entities.moduleClassStudent.ModuleClassStudent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ModuleClassStudentRepositoryAdapter implements ModuleClassStudentRepositoryPort {
    private final ModuleClassStudentJpaRepository moduleClassStudentJpaRepository;

    @Override
    public void create(ModuleClassStudent moduleClassStudent) {
        ModuleClassStudentEntity entity = ModuleClassStudentEntity.builder()
                .moduleClassId(moduleClassStudent.getModuleClassId())
                .studentId(moduleClassStudent.getStudentId())
                .status(moduleClassStudent.getStatus())
                .retakeCount(moduleClassStudent.getRetakeCount())
                .createdAt(moduleClassStudent.getCreatedAt())
                .updatedAt(moduleClassStudent.getUpdatedAt())
                .build();
        moduleClassStudentJpaRepository.save(entity);
    }

    @Override
    public List<ModuleClassStudent> findByStudentId(Long studentId) {
        return moduleClassStudentJpaRepository.findAllByStudentId(studentId)
                .stream()
                .map(entity -> new ModuleClassStudent(
                        entity.getModuleClassStudentId(),
                        entity.getModuleClassId(),
                        entity.getStudentId(),
                        entity.getStatus(),
                        entity.getRetakeCount(),
                        entity.getCreatedAt(),
                        entity.getUpdatedAt()
                ))
                .toList();
    }
}
