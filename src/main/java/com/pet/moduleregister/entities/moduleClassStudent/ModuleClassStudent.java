package com.pet.moduleregister.entities.moduleClassStudent;

import com.pet.moduleregister.entities.moduleClassStudent.enums.LearnStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class ModuleClassStudent {
    private Long moduleClassStudentId;
    private Long moduleClassId;
    private Long studentId;

    private LearnStatus status;
    private int retakeCount;

    private Instant createdAt;
    private Instant updatedAt;
}
