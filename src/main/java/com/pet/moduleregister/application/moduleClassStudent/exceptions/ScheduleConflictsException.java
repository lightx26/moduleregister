package com.pet.moduleregister.application.moduleClassStudent.exceptions;

public class ScheduleConflictsException extends RuntimeException {
    public ScheduleConflictsException(String message) {
        super(message);
    }

    public ScheduleConflictsException(String message, Throwable cause) {
        super(message, cause);
    }
}
