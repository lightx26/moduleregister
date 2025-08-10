package com.pet.moduleregister.infrastructure.adapters.out.moduleClassStudent.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuleClassStudentJpaRepository extends JpaRepository<ModuleClassStudentEntity, Long> {
    List<ModuleClassStudentEntity> findAllByStudentId(Long studentId);
}
