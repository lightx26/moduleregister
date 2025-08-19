package com.pet.moduleregister.infrastructure.adapters.in.web.moduleClass.facade;

import com.pet.moduleregister.application._shared.AuthUser;
import com.pet.moduleregister.application.moduleClass.dto.usecases.OpeningClassDetails;
import com.pet.moduleregister.application.moduleClass.ports.in.usecases.GetMyOpeningModuleClasses;
import com.pet.moduleregister.application.moduleClass.ports.in.usecases.GetOpeningModuleClassDetails;
import com.pet.moduleregister.application.moduleClass.ports.in.usecases.GetOpeningModuleClasses;
import com.pet.moduleregister.infrastructure.adapters.in.web._shared.exceptions.UnauthorizedException;
import com.pet.moduleregister.infrastructure.adapters.in.web._shared.security.AuthUtils;
import com.pet.moduleregister.infrastructure.adapters.in.web.moduleClass.dto.response.openingClass.OpeningClass;
import com.pet.moduleregister.infrastructure.adapters.in.web._shared.dto.response.pagination.CursorPageResponse;
import com.pet.moduleregister.infrastructure.adapters.in.web.moduleClass.dto.response.openingClass.OpeningClassDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ModuleClassFacadeImpl implements ModuleClassFacade {
    private final GetOpeningModuleClasses getOpeningModuleClasses;
    private final GetMyOpeningModuleClasses getMyOpeningModuleClasses;
    private final GetOpeningModuleClassDetails getOpeningModuleClassDetails;
    @Override
    public CursorPageResponse<OpeningClass, Long> getOpeningModuleClasses(Long cursor, int limit) {
        var openingClassesSlice = getOpeningModuleClasses.getOpeningModuleClasses(limit);

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
    public List<OpeningClass> getMyOpeningModuleClasses() {
        AuthUser currentUser = AuthUtils.getCurrentUser().map(
                user -> AuthUser.builder()
                        .userId(user.getUserId())
                        .programId(user.getProgramId())
                        .build()
        ).orElseThrow(() -> new UnauthorizedException("User not authenticated"));

        return getMyOpeningModuleClasses.getMyOpeningModuleClasses(currentUser).stream()
                .map(ResponseMapper::mapToOpeningClass)
                .toList();
    }

    @Override
    public OpeningClassDetailsResponse getOpeningModuleClassDetails(String classCode) {
        OpeningClassDetails classDetails = getOpeningModuleClassDetails.getOpeningModuleClassDetails(classCode);
        return ResponseMapper.mapToOpeningClassDetailsResponse(classDetails);
    }
}
