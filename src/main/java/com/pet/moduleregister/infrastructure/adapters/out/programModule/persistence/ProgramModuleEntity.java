package com.pet.moduleregister.infrastructure.adapters.out.programModule.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
@Entity
@Table(name = "program_module")
@Getter
@Setter
public class ProgramModuleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "program_module_id")
    private Long programModuleId;

    @Column(name = "program_id")
    private Long programId;
    // Compulsory module only
    @Column(name = "module_id")
    private Long moduleId;
    // Elective module only
    @Column(name = "elective_group_id")
    private Long electiveGroupId;

    @Column(name = "semester_order")
    private int semesterOrder;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}
