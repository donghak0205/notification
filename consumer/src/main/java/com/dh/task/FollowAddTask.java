package com.dh.task;

import com.dh.domain.FollowNotification;
import com.dh.utils.NotificationIdGenerator;
import com.dh.service.NotificationSaveService;
import com.dh.domain.NotificationType;
import com.dh.event.FollowEvent;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class FollowAddTask {

    private final NotificationSaveService saveService;

    public FollowAddTask(NotificationSaveService saveService) {
        this.saveService = saveService;
    }

    public void processEvent(FollowEvent event) {
        saveService.insert(createFollowNotification(event));
    }

    private FollowNotification createFollowNotification(FollowEvent event) {
        Instant now = Instant.now();

        return new FollowNotification(
                NotificationIdGenerator.generate(),
                event.getTargetUserId(),
                NotificationType.FOLLOW,
                event.getCreatedAt(),
                now,
                now,
                now.plus(90, ChronoUnit.DAYS),
                event.getUserId()
        );
    }

}
