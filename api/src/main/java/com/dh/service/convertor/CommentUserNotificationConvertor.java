package com.dh.service.convertor;

import com.dh.client.PostClient;
import com.dh.client.UserClient;
import com.dh.domain.CommentNotification;
import com.dh.domain.Post;
import com.dh.domain.User;
import com.dh.service.dto.ConvertedCommentNotification;
import org.springframework.stereotype.Service;

@Service
public class CommentUserNotificationConvertor {

    private final UserClient userClient;
    private final PostClient postClient;

    public CommentUserNotificationConvertor(UserClient userClient, PostClient postClient) {
        this.userClient = userClient;
        this.postClient = postClient;
    }

    public ConvertedCommentNotification convert (CommentNotification notification) {
        User user = userClient.getUser(notification.getWriterId());
        Post post = postClient.getPost(notification.getWriterId());

        return new ConvertedCommentNotification(
                notification.getId(),
                notification.getType(),
                notification.getOccurredAt(),
                notification.getLastUpdatedAt(),
                user.getName(),
                user.getProfileImageUrl(),
                notification.getComment(),
                post.getImageUrl()
        );
    }
}
