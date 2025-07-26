package com.pet.moduleregister.infrastructure.adapters.shared.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/health-check")
public class HealthCheckController {
    @GetMapping
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Application is running");
    }
}
