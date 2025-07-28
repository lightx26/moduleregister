package com.pet.moduleregister.entities.moduleClassStudent;

import com.pet.moduleregister.entities.moduleClassStudent.enums.LearnStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ModuleClassStudent {
    private Long moduleClassStudentId;
    private Long moduleClassId;
    private Long studentId;

    private LearnStatus status;
    private int retakeCount;

    private String createdAt;
    private String updatedAt;
}
