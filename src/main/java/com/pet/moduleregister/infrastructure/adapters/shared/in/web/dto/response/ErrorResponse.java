package com.pet.moduleregister.infrastructure.adapters.shared.in.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ErrorResponse {
    private String errorCode;
    private String message;
    private List<String> details;
    private Instant timestamp;
}
