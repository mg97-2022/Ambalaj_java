package com.Ambalaj.Ambalaj.security;

import com.Ambalaj.Ambalaj.exception.NotFoundException;
import com.Ambalaj.Ambalaj.model.AppUserEntity;
import com.Ambalaj.Ambalaj.service.AppUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final AppUserService appUserService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException, NotFoundException {
        // 1) Get the JWT token from the request headers
        String token = jwtUtil.getJwtFromRequest(request);
        // SHOULD I THROW ERROR HERE THAT TOKEN IS NOT PROVIDED ?? (QUESTION)
        if (token == null) {
//            throw new CustomException("Please Provide JWT Token.", HttpStatus.UNAUTHORIZED);
            filterChain.doFilter(request, response);
            return;
        }
        // 2) Validate if the token is valid and not expired or not
        jwtUtil.validateToken(token);
        // 3) Get the payload from the token
        String userEmail = jwtUtil.getJwtPayload(token);
        // 4) Get the user details from db and save it in security context
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            AppUserEntity userDetails = appUserService.loadUserByUsername(userEmail);
            this.setSecurityContext(userDetails, request);
        }
        // 5) Moves to the next filter
        filterChain.doFilter(request, response);
    }

    private void setSecurityContext(AppUserEntity userDetails, HttpServletRequest request) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        context.setAuthentication(authToken);
        SecurityContextHolder.setContext(context);
    }
}
