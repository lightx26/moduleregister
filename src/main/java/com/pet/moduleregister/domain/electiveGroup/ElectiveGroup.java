package com.pet.moduleregister.domain.electiveGroup;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class ElectiveGroup {
    private Long electiveGroupId;
    private String groupCode;
    private int minModules;
    private Instant createdAt;
    private Instant updatedAt;
}
