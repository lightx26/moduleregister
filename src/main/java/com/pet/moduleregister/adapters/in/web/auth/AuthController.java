package com.pet.moduleregister.adapters.in.web.auth;

import com.pet.moduleregister.adapters.in.web.auth.dto.request.LoginParams;
import com.pet.moduleregister.adapters.in.web.auth.facade.AuthFacade;
import com.pet.moduleregister.adapters.in.web.shared.dto.response.MessageResponse;
import com.pet.moduleregister.application.user.ports.in.dto.LoginResultDTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthFacade authFacade;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginParams loginParams, HttpServletResponse response) {
        LoginResultDTO result = authFacade.login(loginParams);

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

        return ResponseEntity.ok(
                new MessageResponse("Login successful")
        );
    }
}
