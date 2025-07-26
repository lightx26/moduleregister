package com.pet.moduleregister.infrastructure.adapters.electiveGroup.out.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "elective_group")
@Getter
@Setter
public class ElectiveGroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "elective_group_id")
    private Long electiveGroupId;

    @Column(name = "group_code", unique = true)
    private String groupCode;

    @Column(name = "min_modules")
    private int minModules;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}
