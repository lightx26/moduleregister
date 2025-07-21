package com.pet.moduleregister.adapters.in.web.semester.facade;

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
