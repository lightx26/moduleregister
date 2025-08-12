package com.pet.moduleregister.application.moduleClass.services.usecases;

import com.pet.moduleregister.application._shared.AuthUser;
import com.pet.moduleregister.application._shared.exceptions.NotRegistrationTimeException;
import com.pet.moduleregister.application.moduleClass.dto.usecases.*;
import com.pet.moduleregister.application.moduleClass.ports.in.usecases.GetMyOpeningModuleClasses;
import com.pet.moduleregister.application.moduleClass.ports.out.GetCurrentPeriodPort;
import com.pet.moduleregister.application.moduleClass.ports.out.GetModulesOfProgramPort;
import com.pet.moduleregister.application.moduleClass.ports.out.GetSchedulesOfClassPort;
import com.pet.moduleregister.application.moduleClass.ports.out.ModuleClassRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetMyOpeningModuleClassesImpl implements GetMyOpeningModuleClasses {
    private final ModuleClassRepositoryPort moduleClassRepository;
    private final GetModulesOfProgramPort getModulesOfProgram;
    private final GetCurrentPeriodPort getCurrentPeriod;
    private final GetSchedulesOfClassPort getSchedulesOfClass;

    @Override
    public List<PersonalOpeningClass> getMyOpeningModuleClasses(AuthUser currentUser) {
        // Check time for registration period
        RegistrationPeriod period = getCurrentPeriod.getCurrentPeriod()
                .orElseThrow(() -> new NotRegistrationTimeException(
                        "No current registration period found. Please check the registration periods."
                ));

        Map<Long, ModuleInProgram> programModulesMap = getProgramModulesMap(currentUser.getProgramId());
        // Use mutable List to pass to findPersonalOpeningClasses method
        List<Long> moduleIds = new ArrayList<>(programModulesMap.keySet());

        List<PersonalOpeningClass> openingClasses =
                moduleClassRepository.findPersonalOpeningClasses(period.getSemesterId(), moduleIds);

        Map<Long, List<Schedule>> schedulesMap = getSchedulesMap(
                openingClasses.stream()
                        .map(PersonalOpeningClass::getModuleClassId)
                        .collect(Collectors.toList())
        );

        return enrichPersonalOpeningClasses(
                openingClasses,
                programModulesMap,
                schedulesMap
        );
    }

    private Map<Long, ModuleInProgram> getProgramModulesMap(Long programId) {
        List<ModuleInProgram> programModules =
                getModulesOfProgram.getModulesOfProgram(programId);

        if (programModules.isEmpty()) {
            return Map.of(); // Return empty map if no modules found
        }

        return programModules.stream()
                .collect(Collectors.toMap(ModuleInProgram::getModuleId, module -> module));
    }

    private Map<Long, List<Schedule>> getSchedulesMap(List<Long> moduleClassIds) {
        List<Schedule> schedules =
                getSchedulesOfClass.getSchedulesOfClass(moduleClassIds.toArray(Long[]::new));

        return schedules.stream()
                .collect(Collectors.groupingBy(Schedule::getModuleClassId));
    }

    private List<PersonalOpeningClass> enrichPersonalOpeningClasses(
                List<PersonalOpeningClass> openingClasses,
                Map<Long, ModuleInProgram> programModulesMap,
                Map<Long, List<Schedule>> schedulesMap) {

        for (PersonalOpeningClass openingClass : openingClasses) {
            ModuleInProgram module = programModulesMap.get(openingClass.getModuleId());
            openingClass.setModuleName(module.getModuleName());
            openingClass.setNumberOfCredits(module.getNumberOfCredits());
            openingClass.setSchedules(schedulesMap.getOrDefault(openingClass.getModuleClassId(), List.of()));
        }

        return openingClasses;
    }
}
