package com.pet.moduleregister.infrastructure.adapters.out.electiveModule.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "elective_module")
@Getter
@Setter
public class ElectiveModuleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "elective_module_id")
    private Long electiveModuleId;

    @Column(name = "module_id")
    private Long moduleId;

    @Column(name = "elective_group_id")
    private Long electiveGroupId;


    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}
