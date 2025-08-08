package com.pet.moduleregister.application.moduleClass.ports.in;

import com.pet.moduleregister.application.moduleClass.dto.OpeningClassDetails;

public interface GetOpeningModuleClassDetails {
    OpeningClassDetails getOpeningModuleClassDetails(String classCode);
}
