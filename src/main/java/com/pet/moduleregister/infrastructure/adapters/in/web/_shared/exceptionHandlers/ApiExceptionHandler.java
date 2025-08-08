package com.pet.moduleregister.infrastructure.adapters.in.web._shared.exceptionHandlers;

import com.pet.moduleregister.infrastructure.adapters.in.web._shared.dto.response.ErrorResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Order(3)
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
}
