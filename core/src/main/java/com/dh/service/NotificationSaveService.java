package com.dh.service;

import com.dh.domain.Notification;
import com.dh.repository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationSaveService {

    @Autowired
    private NotificationRepository repository;

    public void insert(Notification notification){
        Notification result = repository.insert(notification);
        log.info("inserted : {}", result);
    }

    public void upsert(Notification notification){
        Notification result = repository.save(notification);
    }
}
