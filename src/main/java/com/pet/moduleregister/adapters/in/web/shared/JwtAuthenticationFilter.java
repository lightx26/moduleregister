package com.pet.moduleregister.adapters.in.web.shared;

import com.pet.moduleregister.application.auth.ports.out.TokenServicePort;
import com.pet.moduleregister.application.user.ports.out.UserRepositoryPort;
import com.pet.moduleregister.domain.user.model.User;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenServicePort jwtTokenService;
    private final UserRepositoryPort userRepository;
    public JwtAuthenticationFilter(TokenServicePort jwtTokenService, UserRepositoryPort userRepository) {
        this.jwtTokenService = jwtTokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String token = extractToken(request);
        System.out.println("Extracted token: " + token);
        System.out.println(jwtTokenService.isValidToken(token));
        if (token != null && jwtTokenService.isValidToken(token)) {
            String userId = jwtTokenService.extractUserId(token);
            System.out.println("Extracted userId: " + userId);
            Optional<User> userOpt = userRepository.findById(userId);

            if (userOpt.isEmpty()) {
                filterChain.doFilter(request, response);
                return;
            }

            User user = userOpt.get();
            System.out.println(user);
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(user, null, List.of(new SimpleGrantedAuthority(
                            user.getRole().name()
                    )));
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        System.out.println(Arrays.toString(request.getCookies()));
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
