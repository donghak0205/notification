package com.dh.task;

import com.dh.client.CommentClient;
import com.dh.client.PostClient;
import com.dh.domain.*;
import com.dh.event.CommentEvent;
import com.dh.service.NotificationSaveService;
import com.dh.utils.NotificationIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;


@Slf4j
@Component
//댓글 이벤트 처리 비즈니스로직
public class CommentAddTask {

    @Autowired
    PostClient postClient;

    @Autowired
    CommentClient commentClient;

    @Autowired
    NotificationSaveService saveService;

    public void processEvent(CommentEvent event) {
        //내가 작성한 댓글인 경우 무시
        Post post = postClient.getPost(event.getPostId());
        if(Objects.equals(post.getUserId(),event.getUserId())){
            return;
        }

        Comment comment = commentClient.getComment(event.getCommentId());

        // 알림생성
        Notification notification = createdNotification(post, comment);
        saveService.insert(notification);
        // 저장
    }

    private Notification createdNotification(Post post, Comment comment) {

        Instant now = Instant.now();

        return new CommentNotification(
                NotificationIdGenerator.generate(),
                post.getUserId(),
                NotificationType.COMMENT,
                comment.getCreatedAt(),
                now,
                now,
                now.plus( 90, ChronoUnit.DAYS),
                post.getId(),
                comment.getUserId(),
                comment.getContent(),
                comment.getId()
        );
    }
}
