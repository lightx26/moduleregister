package com.pet.moduleregister.infrastructure.adapters.moduleClass.in.web;

import com.pet.moduleregister.infrastructure.adapters.moduleClass.in.web.dto.response.openingClass.OpeningClass;
import com.pet.moduleregister.infrastructure.adapters.moduleClass.in.web.facade.ModuleClassFacade;
import com.pet.moduleregister.infrastructure.adapters.shared.in.web.dto.response.pagination.CursorPageResponse;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/module-classes")
@RequiredArgsConstructor
public class ModuleClassController {
    private final ModuleClassFacade moduleClassFacade;
    @GetMapping("/opening-classes")
    public ResponseEntity<?> getOpeningModuleClasses(
            @RequestParam(name = "cursor", required = false) Long cursor,
            @RequestParam(name = "limit", defaultValue = "10") @Min(1) @Max(100) int limit) {
        CursorPageResponse<OpeningClass, Long> res = moduleClassFacade.getOpeningModuleClasses(cursor, limit);
        return ResponseEntity.ok(res);
    }
}
