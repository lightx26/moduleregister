package com.pet.moduleregister.infrastructure.adapters.in.web._shared.exceptionHandlers;

import com.pet.moduleregister.infrastructure.adapters.in.web._shared.dto.response.ErrorResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
@Order(2)
public class SecurityExceptionHandler {
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(AccessDeniedException ex) {
        ErrorResponse errRes = ErrorResponse.builder()
                .errorCode("FORBIDDEN")
                .message("Access denied")
                .timestamp(Instant.now())
                .details(List.of(ex.getMessage()))
                .build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errRes);
    }
}
