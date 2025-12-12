package com.dh.task;

import com.dh.domain.LikeNotification;
import com.dh.domain.Notification;
import com.dh.domain.NotificationRemoveService;
import com.dh.event.LikeEvent;
import com.dh.service.NotificationGetService;
import com.dh.service.NotificationSaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

import static com.dh.domain.NotificationType.LIKE;

@Slf4j
@Component
public class LikeRemoveTask {

    @Autowired
    NotificationGetService getService;

    @Autowired
    NotificationRemoveService removeService;

    @Autowired
    NotificationSaveService saveService;

    public void processEvent(LikeEvent event){
        Optional<Notification> optionalNotification = getService.getNotificationByTypeAndPostId(LIKE, event.getPostId());
        if(optionalNotification.isEmpty()) {
            log.error("No notification with postId={}", event.getPostId());
            return;
        }

        //likers에서 event.userId 제거
        // 1. likers가 비어있으면 알림삭제 2. 남아있으면 알림 업데이트
        LikeNotification notification = (LikeNotification) optionalNotification.get();
        removeLikerAndUpdateNotification(notification, event);
//        notification.removeLiker(event.getUserId(), Instant.now());
//
//        if(notification.getLikeIds().isEmpty()){
//            removeService.deleteById(notification.getId());
//        } else{
//            saveService.upsert(notification);
//        }
    }

    private void removeLikerAndUpdateNotification(LikeNotification notification, LikeEvent event){
        notification.removeLiker(event.getUserId(), Instant.now());

        if(notification.getLikeIds().isEmpty()){
            removeService.deleteById(notification.getId());
        } else {
            saveService.upsert(notification);
        }
    }

}
