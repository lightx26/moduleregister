package com.pet.moduleregister.infrastructure.adapters.moduleClass.in.web.facade;

import com.pet.moduleregister.application.moduleClass.ports.in.GetOpeningModuleClassesUsecase;
import com.pet.moduleregister.infrastructure.adapters.moduleClass.in.web.dto.response.openingClass.OpeningClass;
import com.pet.moduleregister.infrastructure.adapters.shared.in.web.dto.response.pagination.CursorPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ModuleClassFacadeImpl implements ModuleClassFacade {
    private final GetOpeningModuleClassesUsecase getOpeningModuleClassesUsecase;
    @Override
    public CursorPageResponse<OpeningClass, Long> getOpeningModuleClasses(int limit) {
        var openingClassesSlice = getOpeningModuleClassesUsecase.getOpeningModuleClasses(limit);

        List<OpeningClass> openingClasses = openingClassesSlice.getContent()
                .stream()
                .map(ResponseMapper::mapToOpeningClass)
                .toList();

        return CursorPageResponse
                .<OpeningClass, Long>builder()
                .content(openingClasses)
                .cursor(null)
                .nextCursor(null)
                .build();
    }
}
