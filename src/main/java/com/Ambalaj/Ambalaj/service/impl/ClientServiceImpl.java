package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.exception.NotFoundException;
import com.Ambalaj.Ambalaj.model.AppUserEntity;
import com.Ambalaj.Ambalaj.model.ClientEntity;
import com.Ambalaj.Ambalaj.repository.ClientRepository;
import com.Ambalaj.Ambalaj.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    public void addClient(ClientEntity client) {
        clientRepository.save(client);
    }

    @Override
    public ClientEntity findByAppUser(AppUserEntity appUser) throws NotFoundException {
        return clientRepository.findByAppUser(appUser).orElseThrow(
                () -> new NotFoundException(String.format("Client with email %s not found.", appUser.getEmail())));
    }
}
