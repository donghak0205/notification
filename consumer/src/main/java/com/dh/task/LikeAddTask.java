package com.dh.task;

import com.dh.client.PostClient;
import com.dh.domain.LikeNotification;
import com.dh.domain.Notification;
import com.dh.domain.Post;
import com.dh.event.LikeEvent;
import com.dh.service.NotificationGetService;
import com.dh.service.NotificationSaveService;
import com.dh.utils.NotificationIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static com.dh.domain.NotificationType.LIKE;


@Slf4j
@Component
public class LikeAddTask {

    @Autowired
    private PostClient postClient;

    @Autowired
    private NotificationGetService getService;

    @Autowired
    private NotificationSaveService saveService;


    //비즈니스로직의 핵심
    public void processEvent(LikeEvent event) {

        Post post = postClient.getPost(event.getPostId());
        if(post == null){
            log.error("Post is null with postId:{}", event.getPostId());
            return;
        }

        if(post.getUserId().equals(event.getUserId())){
            return; //본인의 게시물에는 알림이 뜨지 않게 한다.
        }

        // likeNotification 1. 신규생성 2. 업데이트
        saveService.upsert(createOrUpdateNotification(post, event));
        // likeNotification db 저장

    }

    private Notification createOrUpdateNotification(Post post, LikeEvent event){
        Optional<Notification> optionalNotification = getService.getNotificationByTypeAndPostId(LIKE, post.getId());

        Instant now = Instant.now();
        Instant retention = now.plus(90, ChronoUnit.DAYS);

        if(optionalNotification.isPresent()){
            //업데이트
            return updateNotification((LikeNotification) optionalNotification.get(), event, now, retention);
        } else {
            //신규 생성
            return createNotification(post, event, now, retention);
        }

    }

    private Notification updateNotification(LikeNotification notification, LikeEvent event, Instant now, Instant retention){
        notification.addLiker(event.getUserId(), event.getCreatedAt(), now,  retention);
        return notification;
    }

    private Notification createNotification(Post post, LikeEvent event, Instant now, Instant retention){

        return new LikeNotification(
                NotificationIdGenerator.generate(),
                post.getUserId(),
                LIKE,
                event.getCreatedAt(),
                now,
                now,
                retention,
                post.getId(),
                List.of(event.getUserId())
        );
    }




}
