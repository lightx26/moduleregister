package com.pet.moduleregister.application.moduleClassStudent.ports.out;

public interface CheckModuleClassExistsPort {
    boolean existsByClassCode(String classCode);
}
