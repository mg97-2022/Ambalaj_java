package com.Ambalaj.Ambalaj.repository;

import com.Ambalaj.Ambalaj.interfaces.AppUserExtraDetails;
import com.Ambalaj.Ambalaj.model.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long>, AppUserExtraDetails<ClientEntity> {
}
