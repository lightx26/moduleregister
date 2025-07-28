package com.pet.moduleregister.application.moduleClass.usecases;

import com.pet.moduleregister.application.moduleClass.exceptions.NotRegistrationTimeException;
import com.pet.moduleregister.application.moduleClass.ports.in.GetOpeningModuleClassesUsecase;
import com.pet.moduleregister.application.moduleClass.dto.OpeningClass;
import com.pet.moduleregister.application.moduleClass.ports.out.ModuleClassRepositoryPort;
import com.pet.moduleregister.application.registrationPeriod.ports.in.GetCurrentPeriodUsecase;
import com.pet.moduleregister.application.shared.exceptions.NotFoundException;
import com.pet.moduleregister.entities.registrationPeriod.RegistrationPeriod;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetOpeningModuleClassesImpl implements GetOpeningModuleClassesUsecase {

    private final GetCurrentPeriodUsecase getCurrentPeriodUsecase;
    private final ModuleClassRepositoryPort moduleClassRepository;
    @Override
    public Slice<OpeningClass> getOpeningModuleClasses(int limit) {
        RegistrationPeriod period;
        try {
            period = getCurrentPeriodUsecase.getCurrentPeriod();
        } catch (NotFoundException ignored) {
            throw new NotRegistrationTimeException(
                    "No current registration period found. Please check the registration periods."
            );
        }

        List<OpeningClass> openingClasses =
                moduleClassRepository.findOpeningClassesBySemesterId(period.getSemesterId(), limit + 1);

        Pageable pageable = Pageable.ofSize(limit);
        boolean hasNext = openingClasses.size() > limit;
        if (hasNext) {
            openingClasses.removeLast();
        }
        return new SliceImpl<>(openingClasses, pageable, hasNext);
    }
}
