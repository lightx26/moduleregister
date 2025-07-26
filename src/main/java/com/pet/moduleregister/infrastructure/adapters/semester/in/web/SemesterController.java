package com.pet.moduleregister.infrastructure.adapters.semester.in.web;

import com.pet.moduleregister.infrastructure.adapters.semester.in.web.facade.SemesterFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/semesters")
@RequiredArgsConstructor
public class SemesterController {
    private final SemesterFacade semesterFacade;
    @PatchMapping("/current-semester")
    public ResponseEntity<?> setCurrentSemester(Long semesterId) {
        semesterFacade.setCurrentSemester(semesterId);
        return ResponseEntity.ok().build();
    }
}
