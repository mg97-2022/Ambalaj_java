package com.Ambalaj.Ambalaj.repository;

import com.Ambalaj.Ambalaj.model.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {}
