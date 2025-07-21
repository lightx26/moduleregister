package com.pet.moduleregister.application.moduleClass.usecases;

import com.pet.moduleregister.application.moduleClass.exceptions.NotRegistrationTimeException;
import com.pet.moduleregister.application.moduleClass.ports.in.GetOpeningModuleClassesUsecase;
import com.pet.moduleregister.application.moduleClass.ports.out.ModuleClassRepositoryPort;
import com.pet.moduleregister.application.registrationPeriod.ports.in.GetCurrentPeriodUsecase;
import com.pet.moduleregister.application.semester.ports.in.GetCurrentSemesterUsecase;
import com.pet.moduleregister.application.shared.exceptions.NotFoundException;
import com.pet.moduleregister.domain.moduleClass.ModuleClass;
import com.pet.moduleregister.domain.registrationPeriod.RegistrationPeriod;
import com.pet.moduleregister.domain.semester.Semester;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GetOpeningModuleClassesImpl implements GetOpeningModuleClassesUsecase {

    private final GetCurrentPeriodUsecase getCurrentPeriodUsecase;
    private final ModuleClassRepositoryPort moduleClassRepository;
    @Override
    public List<ModuleClass> getOpeningModuleClasses() {
        RegistrationPeriod period;
        try {
            period = getCurrentPeriodUsecase.getCurrentPeriod();
        } catch (NotFoundException ignored) {
            throw new NotRegistrationTimeException(
                    "No current registration period found. Please check the registration periods."
            );
        }

        return moduleClassRepository.findBySemesterId(period.getSemesterId());
    }
}
