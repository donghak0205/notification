package com.dh.task;

import com.dh.client.PostClient;
import com.dh.domain.NotificationRemoveService;
import com.dh.domain.Post;
import com.dh.event.CommentEvent;
import com.dh.service.NotificationGetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.dh.domain.NotificationType.COMMENT;


@Slf4j
@Component
public class CommentRemoveTask {

    @Autowired
    PostClient postClient;

    @Autowired
    NotificationGetService getService;

    @Autowired
    NotificationRemoveService removeService;

    public void processEvent(CommentEvent event){
        Post post = postClient.getPost(event.getPostId());
        if(Objects.equals(post.getUserId(),event.getUserId())){
            return;
        }

//        getService.getNotification(NotificationType.COMMENT, event.getCommentId())
//                .ifPresentOrElse(
//                        notification -> removeService.deleteById(notification.getId()),
//                                () -> log.error("Notification not found")
//                );
        getService.getNotificationByTypeAndCommentId(COMMENT, event.getCommentId())
                .ifPresent(
                        notification -> removeService.deleteById(notification.getId())
                );
        }
}

