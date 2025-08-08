package com.pet.moduleregister.application.moduleClass.usecases;

import com.pet.moduleregister.application.classSchedule.ports.in.GetSchedulesOfClass;
import com.pet.moduleregister.application.moduleClass.dto.Schedule;
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
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetOpeningModuleClassesImpl implements GetOpeningModuleClassesUsecase {
    private final GetSchedulesOfClass getSchedulesOfClass;

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

        List<Schedule> schedules = getSchedulesOfClass.getSchedulesOfClass(
                openingClasses.stream().map(OpeningClass::getModuleClassId).toArray(Long[]::new)
        ).stream().map(
                schedule -> new Schedule(
                        schedule.getModuleClassId(),
                        schedule.getDayOfWeek(),
                        schedule.getStartPeriod(),
                        schedule.getEndPeriod(),
                        schedule.getRoom()
                )
        ).toList();

        Map<Long, List<Schedule>> schedulesMap = schedules.stream()
                .collect(Collectors.groupingBy(Schedule::getModuleClassId));

        openingClasses.forEach(openingClass -> {
            List<Schedule> classSchedules = schedulesMap.get(openingClass.getModuleClassId());
            openingClass.setSchedules(Objects.requireNonNullElseGet(classSchedules, List::of));
        });

        Pageable pageable = Pageable.ofSize(limit);
        boolean hasNext = openingClasses.size() > limit;
        if (hasNext) {
            openingClasses.removeLast();
        }
        return new SliceImpl<>(openingClasses, pageable, hasNext);
    }
}
