package com.pet.moduleregister.infrastructure.adapters.in.web._shared.security;

import com.pet.moduleregister.application.auth.ports.out.TokenBlackListPort;
import com.pet.moduleregister.application.auth.ports.out.TokenServicePort;
import com.pet.moduleregister.application.user.ports.in.query.GetUserQuery;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final TokenServicePort jwtTokenService;
    private final GetUserQuery getUserQuery;
    private final TokenBlackListPort tokenBlackListService;
    public JwtAuthenticationFilter(TokenServicePort jwtTokenService, GetUserQuery getUserQuery, TokenBlackListPort tokenBlackListService) {
        this.jwtTokenService = jwtTokenService;
        this.getUserQuery = getUserQuery;
        this.tokenBlackListService = tokenBlackListService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String accessToken = extractAccessToken(request);
        if (accessToken != null
                && !tokenBlackListService.isTokenBlacklisted(accessToken)
                && jwtTokenService.isValidToken(accessToken)) {

            Long userId = Long.parseLong(jwtTokenService.extractUserId(accessToken));
            Optional<AuthUser> authUserOpt = getUserQuery.getUserById(userId)
                                                       .map(AuthUtils::mapToAuthUser);

            if (authUserOpt.isEmpty()) {
                filterChain.doFilter(request, response);
                return;
            }

            AuthUser currentUser = authUserOpt.get();
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(currentUser, null, List.of(new SimpleGrantedAuthority(
                            currentUser.getRole().name()
                    )));
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }

    private String extractAccessToken(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return null;
        }

        List<Cookie> cookies = List.of(request.getCookies());
        return cookies.stream()
                .filter(cookie -> "accessToken".equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);
    }
}
