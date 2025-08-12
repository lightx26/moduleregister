package com.pet.moduleregister.application.module.services.query;

import com.pet.moduleregister.application.module.dto.ModuleBasicDTO;
import com.pet.moduleregister.application.module.ports.in.query.GetModulesOfProgramQuery;
import com.pet.moduleregister.application.module.ports.out.ModuleRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetModulesOfProgramQueryImpl implements GetModulesOfProgramQuery {
    private final ModuleRepositoryPort moduleRepository;

    @Override
    @Cacheable(value = "modulesOfProgram", key = "#programId")
    public List<ModuleBasicDTO> getModulesOfProgram(Long programId) {
        return moduleRepository.getModulesOfProgram(programId);
    }
}
