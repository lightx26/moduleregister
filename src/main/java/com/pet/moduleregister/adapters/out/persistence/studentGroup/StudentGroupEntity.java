package com.pet.moduleregister.adapters.out.persistence.studentGroup;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "student_group")
@Getter
@Setter
public class StudentGroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_group_id")
    private Long studentGroupId;

    @Column(name = "group_name", unique = true)
    private String groupName;

    @Column(name = "faculty_id")
    private Long facultyId;

    @Column(name = "cohort_id")
    private Long cohortId;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}
