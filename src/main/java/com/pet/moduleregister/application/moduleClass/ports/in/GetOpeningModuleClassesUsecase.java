package com.pet.moduleregister.application.moduleClass.ports.in;

import com.pet.moduleregister.application.moduleClass.dto.OpeningClass;
import org.springframework.data.domain.Slice;


public interface GetOpeningModuleClassesUsecase {
    Slice<OpeningClass> getOpeningModuleClasses(int limit);
}
