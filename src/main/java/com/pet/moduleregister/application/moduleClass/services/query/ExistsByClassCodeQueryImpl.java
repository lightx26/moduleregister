package com.pet.moduleregister.application.moduleClass.services.query;

import com.pet.moduleregister.application.moduleClass.ports.in.query.ExistsByClassCodeQuery;
import com.pet.moduleregister.application.moduleClass.ports.out.ModuleClassRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExistsByClassCodeQueryImpl implements ExistsByClassCodeQuery {
    private final ModuleClassRepositoryPort moduleClassRepository;
    @Override
    public boolean existsByClassCode(String classCode) {
        return moduleClassRepository.existsByCode(classCode);
    }
}
