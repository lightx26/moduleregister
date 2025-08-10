package com.pet.moduleregister.application.moduleClass.ports.in.usecases;

import com.pet.moduleregister.application.moduleClass.dto.usecases.OpeningClassDetails;

public interface GetOpeningModuleClassDetails {
    OpeningClassDetails getOpeningModuleClassDetails(String classCode);
}
