package com.pet.moduleregister.infrastructure.adapters.program.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/programs")
public class ProgramController {
    @GetMapping
    public String getPrograms() {
        return "List of programs"; // Placeholder response, replace with actual implementation
    }

    @GetMapping("/{programId}")
    public ResponseEntity<?> getProgramDetails(@PathVariable Long programId) {
        // Placeholder response, replace with actual implementation
        return ResponseEntity.ok("Details of program with ID: " + programId);
    }
}
