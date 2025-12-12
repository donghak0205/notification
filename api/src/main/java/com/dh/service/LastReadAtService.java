package com.dh.service;

import com.dh.repository.NotificationReadRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class LastReadAtService {

    private final NotificationReadRepository repository;

    public LastReadAtService(NotificationReadRepository repository) {
        this.repository = repository;
    }

    public Instant setLastReadAt(long userId){
        return repository.setLastReadAt(userId);
    }
}
