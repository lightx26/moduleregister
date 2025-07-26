package com.pet.moduleregister.infrastructure.adapters.moduleClass.in.web.facade;

import com.pet.moduleregister.infrastructure.adapters.moduleClass.in.web.dto.response.openingClass.OpeningClass;
import com.pet.moduleregister.infrastructure.adapters.shared.in.web.dto.response.pagination.CursorPageResponse;

public interface ModuleClassFacade {
    CursorPageResponse<OpeningClass, Long> getOpeningModuleClasses(Long cursor, int limit);
}
