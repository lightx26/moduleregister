package com.pet.moduleregister.infrastructure.adapters.shared.in.web;

import com.pet.moduleregister.infrastructure.adapters.shared.in.web.dto.response.ErrorResponse;
import com.pet.moduleregister.application.shared.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleServerError(RuntimeException ex) {
        ErrorResponse errRes = ErrorResponse.builder()
                .errorCode("INTERNAL_SERVER_ERROR")
                .message(ex.getMessage() != null ? ex.getMessage() : "An unexpected error occurred")
                .details(new ArrayList<>())
                .timestamp(Instant.now())
                .build();

        return ResponseEntity.internalServerError().body(errRes);
    }

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

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        Throwable mostSpecificCause = ex.getMostSpecificCause();
        String message = mostSpecificCause.getMessage();

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode("INVALID_REQUEST_BODY")
                .message(message)
                .details(List.of())
                .build();

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        ErrorResponse errRes = ErrorResponse.builder()
                .errorCode("VALIDATION_ERROR")
                .message(ex.getMessage())
                .details(ex.getBindingResult().getFieldErrors().stream()
                        .map(error -> error.getField() + ": " + error.getDefaultMessage())
                        .toList())
                .timestamp(Instant.now())
                .build();

        return ResponseEntity.badRequest().body(errRes);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        ErrorResponse errRes = ErrorResponse.builder()
                .errorCode("NOT_FOUND")
                .message(ex.getMessage())
                .details(new ArrayList<>())
                .timestamp(Instant.now())
                .build();

        return ResponseEntity.status(404).body(errRes);
    }
}
