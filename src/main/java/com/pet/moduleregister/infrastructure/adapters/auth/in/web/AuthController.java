package com.pet.moduleregister.infrastructure.adapters.auth.in.web;

import com.pet.moduleregister.infrastructure.adapters.auth.in.web.dto.request.LoginParams;
import com.pet.moduleregister.infrastructure.adapters.auth.in.web.dto.request.RefreshParams;
import com.pet.moduleregister.infrastructure.adapters.auth.in.web.facade.AuthFacade;
import com.pet.moduleregister.infrastructure.adapters.shared.in.web.dto.response.ErrorResponse;
import com.pet.moduleregister.infrastructure.adapters.shared.in.web.dto.response.MessageResponse;
import com.pet.moduleregister.application.auth.ports.in.dto.LoginResultDTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthFacade authFacade;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginParams loginParams, HttpServletResponse response) {
        LoginResultDTO result = authFacade.login(loginParams);

        addAuthToCookie(response, result);

        return ResponseEntity.ok(
                new MessageResponse("Login successful")
        );
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@CookieValue(value = "refreshToken") String refreshToken, HttpServletResponse response) {
        LoginResultDTO result = authFacade.refresh(refreshToken);

        addAuthToCookie(response, result);

        return ResponseEntity.ok(
                new MessageResponse("Token refreshed successfully")
        );
    }

    @DeleteMapping("/logout")
    public ResponseEntity<?> logout(
            @CookieValue(value = "accessToken") String accessToken,
            @CookieValue(value = "refreshToken", required = false) String refreshToken,
           HttpServletResponse response) {

        try {
            authFacade.logout(accessToken, refreshToken);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    ErrorResponse.builder()
                            .errorCode("LOGOUT_FAILED")
                            .message("Logout failed: " + e.getMessage())
                            .details(List.of(
                                    "Ensure you are logged in",
                                    "Tokens may have expired or is invalid"
                            ))
                            .timestamp(Instant.now())
                            .build()
            );
        } finally {
            // Ensure cookies are cleared even if logout fails
            Cookie accessCookie = new Cookie("accessToken", null);
            accessCookie.setMaxAge(0);
            accessCookie.setPath("/");

            Cookie refreshCookie = new Cookie("refreshToken", null);
            refreshCookie.setMaxAge(0);
            refreshCookie.setPath("/api/v1/auth/refresh");

            response.addCookie(accessCookie);
            response.addCookie(refreshCookie);
        }

        return ResponseEntity.ok(
                new MessageResponse("Logout successful")
        );
    }

    private void addAuthToCookie(HttpServletResponse response, LoginResultDTO result) {
        Cookie accessCookie = new Cookie("accessToken", result.getAccessToken());
        accessCookie.setHttpOnly(true);
        accessCookie.setSecure(true);
        accessCookie.setPath("/");
        accessCookie.setMaxAge(15 * 60);

        Cookie refreshCookie = new Cookie("refreshToken", result.getRefreshToken());
        refreshCookie.setHttpOnly(true);
        refreshCookie.setSecure(true);
        refreshCookie.setPath("/api/v1/auth/refresh");
        refreshCookie.setMaxAge(30 * 24 * 60 * 60);

        response.addCookie(accessCookie);
        response.addCookie(refreshCookie);
    }
}
