package com.dh.task;

import com.dh.service.NotificationGetService;
import com.dh.domain.NotificationRemoveService;
import com.dh.domain.NotificationType;
import com.dh.event.FollowEvent;
import org.springframework.stereotype.Component;

@Component
public class FollowRemoveTask {

    private final NotificationGetService getService;

    private final NotificationRemoveService removeService;

    public FollowRemoveTask(NotificationGetService getService, NotificationRemoveService removeService) {
        this.getService = getService;
        this.removeService = removeService;
    }

    public void processEvent(FollowEvent event)  {
        getService.getNotificationByTypeAndFollowerId(NotificationType.FOLLOW, event.getTargetUserId(), event.getUserId())
                .ifPresent(
                        notification -> removeService.deleteById(notification.getId())
                );
    }

}
