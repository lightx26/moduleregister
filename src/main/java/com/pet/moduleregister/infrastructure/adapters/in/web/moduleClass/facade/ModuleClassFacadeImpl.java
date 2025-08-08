package com.pet.moduleregister.infrastructure.adapters.in.web.moduleClass.facade;

import com.pet.moduleregister.application.moduleClass.dto.OpeningClassDetails;
import com.pet.moduleregister.application.moduleClass.ports.in.GetOpeningModuleClassDetails;
import com.pet.moduleregister.application.moduleClass.ports.in.GetOpeningModuleClassesUsecase;
import com.pet.moduleregister.infrastructure.adapters.in.web.moduleClass.dto.response.openingClass.OpeningClass;
import com.pet.moduleregister.infrastructure.adapters.in.web._shared.dto.response.pagination.CursorPageResponse;
import com.pet.moduleregister.infrastructure.adapters.in.web.moduleClass.dto.response.openingClass.OpeningClassDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ModuleClassFacadeImpl implements ModuleClassFacade {
    private final GetOpeningModuleClassesUsecase getOpeningModuleClassesUsecase;
    private final GetOpeningModuleClassDetails getOpeningModuleClassDetailsUsecase;
    @Override
    public CursorPageResponse<OpeningClass, Long> getOpeningModuleClasses(Long cursor, int limit) {
        var openingClassesSlice = getOpeningModuleClassesUsecase.getOpeningModuleClasses(limit);

        List<OpeningClass> openingClasses = openingClassesSlice.getContent()
                .stream()
                .map(ResponseMapper::mapToOpeningClass)
                .toList();

        return CursorPageResponse
                .<OpeningClass, Long>builder()
                .content(openingClasses)
                .isFirst(cursor == null)
                .isLast(openingClassesSlice.isLast())
                .numberOfElements(openingClassesSlice.getNumberOfElements())
                .cursor(null)
                .size(limit)
                .nextCursor(null)
                .build();
    }

    @Override
    public OpeningClassDetailsResponse getOpeningModuleClassDetails(String classCode) {
        OpeningClassDetails classDetails = getOpeningModuleClassDetailsUsecase.getOpeningModuleClassDetails(classCode);
        return ResponseMapper.mapToOpeningClassDetailsResponse(classDetails);
    }
}
