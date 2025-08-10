package com.pet.moduleregister.application.moduleClassStudent.exceptions;

public class DuplicatedRegistrationException extends RuntimeException {
    public DuplicatedRegistrationException(String message) {
        super(message);
    }

    public DuplicatedRegistrationException(String message, Throwable cause) {
        super(message, cause);
    }
}
