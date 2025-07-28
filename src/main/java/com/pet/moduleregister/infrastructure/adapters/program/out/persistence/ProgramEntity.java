package com.pet.moduleregister.infrastructure.adapters.program.out.persistence;

import com.pet.moduleregister.entities.program.enums.Language;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "program")
@Getter
@Setter
public class ProgramEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "program_id")
    private Long programId;

    @Column(name = "program_code", unique = true)
    private String programCode;

    @Column(name = "program_name")
    private String programName;

    @Column(name = "number_of_semesters")
    private int numberOfSemesters;

    @Column(name = "total_credits")
    private int totalCredits;

    @Column(name = "compulsory_credits")
    private int compulsoryCredits;

    @Column(name = "responsible_faculty")
    private Long responsibleFaculty;

    @Column(name = "language")
    @Enumerated(EnumType.STRING)
    private Language language;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}
