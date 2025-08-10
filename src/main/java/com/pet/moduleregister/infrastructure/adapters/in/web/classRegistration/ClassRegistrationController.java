package com.pet.moduleregister.infrastructure.adapters.in.web.classRegistration;

import com.pet.moduleregister.infrastructure.adapters.in.web.classRegistration.dto.request.ClassRegistrationParams;
import com.pet.moduleregister.infrastructure.adapters.in.web.classRegistration.facade.ClassRegistrationFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/class-registrations")
@RequiredArgsConstructor
public class ClassRegistrationController {
    private final ClassRegistrationFacade classRegistrationFacade;
    @PostMapping()
    public ResponseEntity<?> registerClass(@RequestBody @Valid ClassRegistrationParams classRegistrationParams) {
        var res = classRegistrationFacade.registerClass(classRegistrationParams);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/bulk")
    public ResponseEntity<?> registerClassesInBulk() {
        // Placeholder for bulk class registration logic
        return ResponseEntity.ok("Bulk class registration successful"); // Replace with actual implementation
    }
}
