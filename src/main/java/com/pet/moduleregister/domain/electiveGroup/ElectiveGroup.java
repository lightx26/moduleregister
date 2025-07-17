package com.pet.moduleregister.domain.electiveGroup;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ElectiveGroup {
    private Long electiveGroupId;
    private String groupCode;
    private int minModuleCount;
}
