package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.model.SubscriptionEntity;
import com.Ambalaj.Ambalaj.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public void addSubscription(SubscriptionEntity subscriptionEntity) {
        subscriptionRepository.save(subscriptionEntity);
    }
}
