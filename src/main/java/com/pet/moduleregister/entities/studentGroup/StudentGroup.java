package com.pet.moduleregister.entities.studentGroup;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class StudentGroup {
    private Long studentGroupId;
    private String groupName;
    private Long facultyId;
    private Long cohortId;

    private Instant createdAt;
    private Instant updatedAt;
}
