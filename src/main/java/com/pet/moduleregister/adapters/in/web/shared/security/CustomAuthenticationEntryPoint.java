package com.pet.moduleregister.adapters.in.web.shared.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.moduleregister.adapters.in.web.shared.dto.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        ErrorResponse errResponse = ErrorResponse.builder()
                .errorCode("UNAUTHORIZED")
                .message("Unauthorized access")
                .details(List.of(authException.getMessage()))
                .timestamp(Instant.now())
                .build();

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
        response.setContentType("application/json");
        String jsonResponse = objectMapper.writeValueAsString(errResponse);
        response.getWriter().write(jsonResponse);
    }
}

