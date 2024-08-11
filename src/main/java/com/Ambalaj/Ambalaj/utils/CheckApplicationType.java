package com.Ambalaj.Ambalaj.utils;

import com.Ambalaj.Ambalaj.enums.AppUserType;
import com.Ambalaj.Ambalaj.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class CheckApplicationType {
    public void checkWebsiteUser(AppUserType appUserType) throws CustomException {
        if (AppUserType.COMPANY.equals(appUserType) || AppUserType.CLIENT.equals(appUserType)) return;
        throw new CustomException("You are not allowed to perform this action.", HttpStatus.BAD_REQUEST);
    }

    public void checkDashboardUser(AppUserType appUserType) throws CustomException {
        if (AppUserType.ADMIN.equals(appUserType)) return;
        throw new CustomException("You are not allowed to perform this action.", HttpStatus.BAD_REQUEST);
    }
}
