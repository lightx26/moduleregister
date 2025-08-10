package com.pet.moduleregister.infrastructure.adapters.in.web._shared.exceptionHandlers;

import com.pet.moduleregister.application._shared.exceptions.NotFoundException;
import com.pet.moduleregister.application._shared.exceptions.NotRegistrationTimeException;
import com.pet.moduleregister.application.moduleClassStudent.exceptions.DuplicatedRegistrationException;
import com.pet.moduleregister.application.moduleClassStudent.exceptions.NoAvailableSlotsException;
import com.pet.moduleregister.application.moduleClassStudent.exceptions.ScheduleConflictsException;
import com.pet.moduleregister.infrastructure.adapters.in.web._shared.dto.response.ErrorResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.ArrayList;

@RestControllerAdvice
@Order(1)
public class ApplicationExceptionHandler {
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

    @ExceptionHandler(NotRegistrationTimeException.class)
    public ResponseEntity<ErrorResponse> handleNotRegistrationTimeException(NotRegistrationTimeException ex) {
        ErrorResponse errRes = ErrorResponse.builder()
                .errorCode("NOT_REGISTRATION_TIME")
                .message(ex.getMessage())
                .details(new ArrayList<>())
                .timestamp(Instant.now())
                .build();

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errRes);
    }

    @ExceptionHandler(DuplicatedRegistrationException.class)
    public ResponseEntity<ErrorResponse> handleDuplicatedRegistrationException(DuplicatedRegistrationException ex) {
        ErrorResponse errRes = ErrorResponse.builder()
                .errorCode("DUPLICATED_REGISTRATION")
                .message(ex.getMessage())
                .details(new ArrayList<>())
                .timestamp(Instant.now())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errRes);
    }

    @ExceptionHandler(NoAvailableSlotsException.class)
    public ResponseEntity<ErrorResponse> handleNoAvailableSlotsException(NoAvailableSlotsException ex) {
        ErrorResponse errRes = ErrorResponse.builder()
                .errorCode("OUT_OF_SLOTS")
                .message(ex.getMessage())
                .details(new ArrayList<>())
                .timestamp(Instant.now())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errRes);
    }

    @ExceptionHandler(ScheduleConflictsException.class)
    public ResponseEntity<ErrorResponse> handleScheduleConflictsException(ScheduleConflictsException ex) {
        ErrorResponse errRes = ErrorResponse.builder()
                .errorCode("SCHEDULE_CONFLICTS")
                .message(ex.getMessage())
                .details(new ArrayList<>())
                .timestamp(Instant.now())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errRes);
    }
}
