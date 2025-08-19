package com.pet.moduleregister.configurations;

import com.pet.moduleregister.application.user.ports.in.query.GetUserQuery;
import com.pet.moduleregister.infrastructure.adapters.in.web._shared.security.CustomAuthenticationEntryPoint;
import com.pet.moduleregister.infrastructure.adapters.in.web._shared.security.JwtAuthenticationFilter;
import com.pet.moduleregister.application.auth.ports.out.TokenBlackListPort;
import com.pet.moduleregister.application.auth.ports.out.TokenServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtFilter(TokenServicePort jwtTokenService, GetUserQuery getUserQuery, TokenBlackListPort tokenBlackListService) {
        return new JwtAuthenticationFilter(jwtTokenService, getUserQuery, tokenBlackListService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   JwtAuthenticationFilter jwtFilter,
                                                   CustomAuthenticationEntryPoint authEntryPoint) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .headers(header ->
                        header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
                )
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers(
                        "v1/health-check",
                        "v1/auth/login",
                        "v1/auth/refresh",
                        "v1/module-classes/opening-classes",
                        "v1/module-classes/opening-classes/**"
                    )
                    .permitAll();
                    authorize.anyRequest().authenticated();
                })
                .exceptionHandling(exception ->
                    exception.authenticationEntryPoint(authEntryPoint)
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
