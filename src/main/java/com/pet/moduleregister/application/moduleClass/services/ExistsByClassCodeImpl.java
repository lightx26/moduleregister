package com.pet.moduleregister.application.moduleClass.services;

import com.pet.moduleregister.application.moduleClass.ports.in.query.ExistsByClassCode;
import com.pet.moduleregister.application.moduleClass.ports.out.ModuleClassRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExistsByClassCodeImpl implements ExistsByClassCode {
    private final ModuleClassRepositoryPort moduleClassRepository;
    @Override
    public boolean existsByClassCode(String classCode) {
        return moduleClassRepository.existsByCode(classCode);
    }
}
