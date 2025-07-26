package com.pet.moduleregister.infrastructure.adapters.semester.in.web.facade;

import com.pet.moduleregister.application.semester.ports.in.SetCurrentSemesterUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SemesterFacadeImpl implements SemesterFacade {

    private final SetCurrentSemesterUsecase setCurrentSemesterUsecase;

    @Override
    public void setCurrentSemester(Long semesterId) {
        setCurrentSemesterUsecase.setCurrentSemester(semesterId);
    }
}
