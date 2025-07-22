package com.pet.moduleregister.adapters.in.web.moduleClass;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/module-classes")
public class ModuleClassController {
    @GetMapping()
    public ResponseEntity<?> getOpeningModuleClasses() {
        // This method should return all module classes.
        // The actual implementation is not provided in the original code.
        return ResponseEntity.ok().build();
    }
}
