package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.exception.NotFoundException;
import com.Ambalaj.Ambalaj.model.AdminEntity;
import com.Ambalaj.Ambalaj.model.AppUserEntity;
import com.Ambalaj.Ambalaj.repository.AdminRepository;
import com.Ambalaj.Ambalaj.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

    @Override
    public AdminEntity findByAppUser(AppUserEntity appUser) throws NotFoundException {
        return adminRepository.findByAppUser(appUser).orElseThrow(() -> new NotFoundException("Admin"));
    }
}
