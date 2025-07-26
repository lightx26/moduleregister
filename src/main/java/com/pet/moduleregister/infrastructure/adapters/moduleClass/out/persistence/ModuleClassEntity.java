package com.pet.moduleregister.infrastructure.adapters.moduleClass.out.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "module_class")
@Getter
@Setter
public class ModuleClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "module_class_id")
    private Long moduleClassId;

    @Column(name = "module_class_code", unique = true)
    private String moduleClassCode;

    @Column(name = "max_participants")
    private int maxParticipants;

    @Column(name = "module_id")
    private Long moduleId;

    @Column(name = "lecturer_id")
    private Long lecturerId;

    @Column(name = "semester_id")
    private Long semesterId;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}
