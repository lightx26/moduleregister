package com.pet.moduleregister.infrastructure.adapters.in.web.moduleClass.facade;

import com.pet.moduleregister.infrastructure.adapters.in.web.moduleClass.dto.response.openingClass.OpeningClass;
import com.pet.moduleregister.infrastructure.adapters.in.web._shared.dto.response.pagination.CursorPageResponse;
import com.pet.moduleregister.infrastructure.adapters.in.web.moduleClass.dto.response.openingClass.OpeningClassDetailsResponse;

public interface ModuleClassFacade {
    CursorPageResponse<OpeningClass, Long> getOpeningModuleClasses(Long cursor, int limit);
    OpeningClassDetailsResponse getOpeningModuleClassDetails(String classCode);
}
