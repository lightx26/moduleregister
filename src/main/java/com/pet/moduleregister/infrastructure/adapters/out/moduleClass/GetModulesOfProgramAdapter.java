package com.pet.moduleregister.infrastructure.adapters.out.moduleClass;

import com.pet.moduleregister.application.module.ports.in.query.GetModulesOfProgramQuery;
import com.pet.moduleregister.application.moduleClass.dto.usecases.ModuleInProgram;
import com.pet.moduleregister.application.moduleClass.ports.out.GetModulesOfProgramPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("getModulesOfProgramAdapterMC")
@RequiredArgsConstructor
public class GetModulesOfProgramAdapter implements GetModulesOfProgramPort {
    private final GetModulesOfProgramQuery getModulesOfProgramQuery;

    @Override
    public List<ModuleInProgram> getModulesOfProgram(Long programId) {
        return getModulesOfProgramQuery.getModulesOfProgram(programId)
                .stream().map(
                        module -> new ModuleInProgram(
                                module.getModuleId(),
                                module.getModuleCode(),
                                module.getModuleName(),
                                module.getNumberOfCredits(),
                                module.isCompulsory()
                        )
                ).toList();
    }
}
