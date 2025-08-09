package com.pet.moduleregister.infrastructure.adapters.in.web.semester.facade;

import com.pet.moduleregister.application.semester.ports.in.usecases.SetCurrentSemester;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SemesterFacadeImpl implements SemesterFacade {

    private final SetCurrentSemester setCurrentSemesterUsecase;

    @Override
    public void setCurrentSemester(Long semesterId) {
        setCurrentSemesterUsecase.setCurrentSemester(semesterId);
    }
}
