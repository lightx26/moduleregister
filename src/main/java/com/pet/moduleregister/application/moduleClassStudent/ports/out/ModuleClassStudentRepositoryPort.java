package com.pet.moduleregister.application.moduleClassStudent.ports.out;

import com.pet.moduleregister.entities.moduleClassStudent.ModuleClassStudent;

import java.util.List;

public interface ModuleClassStudentRepositoryPort {
    void create(ModuleClassStudent moduleClassStudent);
    List<ModuleClassStudent> findByStudentId(Long studentId);
}
