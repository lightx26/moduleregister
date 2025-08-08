package com.pet.moduleregister.infrastructure.adapters.in.web._shared.exceptions;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
