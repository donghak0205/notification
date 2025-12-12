package com.dh.service.convertor;

import com.dh.client.PostClient;
import com.dh.client.UserClient;
import com.dh.domain.CommentNotification;
import com.dh.domain.LikeNotification;
import com.dh.domain.Post;
import com.dh.domain.User;
import com.dh.service.dto.ConvertedCommentNotification;
import com.dh.service.dto.ConvertedLikeNotification;
import org.springframework.stereotype.Service;

@Service
public class LikeUserNotificationConvertor {

    private final UserClient userClient;
    private final PostClient postClient;

    public LikeUserNotificationConvertor(UserClient userClient, PostClient postClient) {
        this.userClient = userClient;
        this.postClient = postClient;
    }

    public ConvertedLikeNotification convert (LikeNotification notification) {
        User user = userClient.getUser(notification.getLikeIds().getLast());
        Post post = postClient.getPost(notification.getPostId());

        return new ConvertedLikeNotification(
                notification.getId(),
                notification.getType(),
                notification.getOccurredAt(),
                notification.getLastUpdatedAt(),
                user.getName(),
                user.getProfileImageUrl(),
                notification.getLikeIds().size(),
                post.getImageUrl()
        );
    }
}
