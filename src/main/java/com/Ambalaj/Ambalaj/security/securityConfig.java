package com.Ambalaj.Ambalaj.security;

import com.Ambalaj.Ambalaj.enums.AppUserRole;
import com.Ambalaj.Ambalaj.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class securityConfig {
    private final JwtAuthFilter jwtAuthFilter;
    private final GlobalExceptionFilter globalExceptionFilter;
    private final AppUserService appUserService;
    private final AccessDeniedHandler AccessDeniedHandlerImpl;
    private final AuthenticationEntryPoint AuthenticationEntryPointImpl;

    @Bean
    SecurityFilterChain securityFilterChain(@NotNull HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults()) // Enable CORS
                .authorizeHttpRequests((requests) -> requests.requestMatchers("/api/v*/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v*/country").permitAll()
                        .requestMatchers("/api/v*/country/**").hasRole(AppUserRole.ADMIN.name())
                        .requestMatchers("/swagger-ui/**").permitAll().requestMatchers("/api-docs/**").permitAll()
                        .anyRequest().authenticated()).csrf(AbstractHttpConfigurer::disable) // Disable CSRF
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider()).exceptionHandling(
                        exceptionHandlingConfigurer -> exceptionHandlingConfigurer.authenticationEntryPoint(
                                AuthenticationEntryPointImpl).accessDeniedHandler(AccessDeniedHandlerImpl))
                .addFilterBefore(globalExceptionFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(appUserService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(@NotNull AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
