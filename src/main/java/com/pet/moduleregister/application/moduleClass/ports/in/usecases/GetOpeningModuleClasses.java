package com.pet.moduleregister.application.moduleClass.ports.in.usecases;

import com.pet.moduleregister.application.moduleClass.dto.usecases.OpeningClass;
import org.springframework.data.domain.Slice;


public interface GetOpeningModuleClasses {
    Slice<OpeningClass> getOpeningModuleClasses(int limit);
}
