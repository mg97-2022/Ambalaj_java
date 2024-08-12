package com.Ambalaj.Ambalaj.service;

import com.Ambalaj.Ambalaj.model.ClientEntity;

public interface ClientService extends AppUserExtraDetailsService<ClientEntity> {
    void addClient(ClientEntity client);
}
