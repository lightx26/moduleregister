package com.pet.moduleregister.infrastructure.adapters.in.web.moduleClass;

import com.pet.moduleregister.infrastructure.adapters.in.web._shared.exceptions.TypeMismatchException;
import com.pet.moduleregister.infrastructure.adapters.in.web.moduleClass.dto.response.openingClass.OpeningClass;
import com.pet.moduleregister.infrastructure.adapters.in.web.moduleClass.facade.ModuleClassFacade;
import com.pet.moduleregister.infrastructure.adapters.in.web._shared.dto.response.pagination.CursorPageResponse;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/module-classes")
@RequiredArgsConstructor
public class ModuleClassController {
    private final ModuleClassFacade moduleClassFacade;
    @GetMapping("/opening-classes")
    public ResponseEntity<?> getOpeningModuleClasses(
            @RequestParam(name = "cursor", required = false) Long cursor,
            @RequestParam(name = "limit", defaultValue = "10") @Min(1) @Max(100) int limit) {
        var res = moduleClassFacade.getOpeningModuleClasses(cursor, limit);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/opening-classes/{id}")
    public ResponseEntity<?> getOpeningModuleClassDetails(@PathVariable String id) {
        var res = moduleClassFacade.getOpeningModuleClassDetails(id);
        return ResponseEntity.ok(res);
    }
}
