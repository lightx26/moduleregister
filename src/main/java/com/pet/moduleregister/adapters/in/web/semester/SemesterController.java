package com.pet.moduleregister.adapters.in.web.semester;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/semesters")
@RequiredArgsConstructor
public class SemesterController {
    @PatchMapping("/current-semester")
    public void setCurrentSemester(Long semesterId) {
        // This method will be implemented later to set the current semester
        // using the SetCurrentSemesterUsecase.
    }
}
