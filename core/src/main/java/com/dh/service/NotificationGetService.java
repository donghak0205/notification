package com.dh.service;

import com.dh.domain.Notification;
import com.dh.repository.NotificationRepository;
import com.dh.domain.NotificationType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class NotificationGetService {

    @Autowired
    private NotificationRepository repository;

//    public Optional<Notification> getNotification(NotificationType type, Long commentId){
//        return repository.findByTypeAndCommentId(type, commentId);
//    }

    public Optional<Notification> getNotificationByTypeAndCommentId(NotificationType type, Long commentId){
        return repository.findByTypeAndCommentId(type, commentId);
    }

    public Optional<Notification> getNotificationByTypeAndPostId(NotificationType type, Long postId){
        return repository.findByTypeAndPostId(type, postId);
    }

    public Optional<Notification> getNotificationByTypeAndFollowerId(NotificationType type, Long userId, Long followerId){
        return repository.findByTypeAndUserIdAndFollowerId(type, userId, followerId);
    }
}
