package com.pet.moduleregister.infrastructure.adapters.out.moduleClassStudent;

import com.pet.moduleregister.application.moduleClass.ports.in.query.ExistsByClassCodeQuery;
import com.pet.moduleregister.application.moduleClassStudent.ports.out.CheckModuleClassExistsPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckModuleClassExistsAdapter implements CheckModuleClassExistsPort {
    private final ExistsByClassCodeQuery existsByClassCode;

    @Override
    public boolean existsByClassCode(String classCode) {
        return existsByClassCode.existsByClassCode(classCode);
    }
}
