package com.pet.moduleregister.infrastructure.adapters.shared.in.web.exceptions;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
