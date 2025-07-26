package com.pet.moduleregister.infrastructure.adapters.moduleClass.in.web;

import com.pet.moduleregister.infrastructure.adapters.moduleClass.in.web.facade.ModuleClassFacade;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
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
    @GetMapping()
    public ResponseEntity<?> getOpeningModuleClasses(
            @RequestParam(name = "limit", defaultValue = "10") @Min(1) @Max(100) int limit) {
        moduleClassFacade.getOpeningModuleClasses(limit);
        return ResponseEntity.ok().build();
    }
}
