package com.Ambalaj.Ambalaj.security;

import com.Ambalaj.Ambalaj.model.AppUserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CurrentUser {

    public static AppUserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof AppUserEntity) {
                return (AppUserEntity) principal;
            }
        }

        return null;
    }
}
