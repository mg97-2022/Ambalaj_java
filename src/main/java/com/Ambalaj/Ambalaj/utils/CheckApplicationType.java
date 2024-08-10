package com.Ambalaj.Ambalaj.utils;

import com.Ambalaj.Ambalaj.enums.AppUserRole;
import com.Ambalaj.Ambalaj.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class CheckApplicationType {
    public void checkWebsiteUser(AppUserRole appUserRole) throws CustomException {
        if (AppUserRole.COMPANY.equals(appUserRole) || AppUserRole.CLIENT.equals(appUserRole)) return;
        throw new CustomException("You are not allowed to perform this action.", HttpStatus.BAD_REQUEST);
    }

    public void checkDashboardUser(AppUserRole appUserRole) throws CustomException {
        if (AppUserRole.ADMIN.equals(appUserRole)) return;
        throw new CustomException("You are not allowed to perform this action.", HttpStatus.BAD_REQUEST);
    }
}
