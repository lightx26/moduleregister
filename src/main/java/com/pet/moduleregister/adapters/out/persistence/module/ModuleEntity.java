package com.pet.moduleregister.adapters.out.persistence.module;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "module")
@Getter
@Setter
public class ModuleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "module_id")
    private Long moduleId;

    @Column(name = "module_code", unique = true)
    private String moduleCode;

    @Column(name = "name")
    private String name;

    @Column(name = "number_of_credits")
    private int numberOfCredits;

    @Column(name = "is_compulsory")
    private boolean isCompulsory;

    @Column(name = "responsible_faculty")
    private Long responsibleFaculty;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}
