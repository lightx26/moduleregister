package com.pet.moduleregister.infrastructure.adapters.studentCohort.out.persistence;

import com.pet.moduleregister.entities.studentCohort.enums.DegreeProgram;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "student_cohort")
@Getter
@Setter
public class StudentCohortEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_cohort_id")
    private Long studentCohortId;

    @Column(name = "cohort_name", unique = true)
    private String cohortName;

    @Column(name = "entry_year")
    private int entryYear;

    @Column(name = "degree_program")
    @Enumerated(EnumType.STRING)
    private DegreeProgram degreeProgram;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}
