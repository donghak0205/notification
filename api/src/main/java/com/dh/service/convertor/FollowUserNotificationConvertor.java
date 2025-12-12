package com.dh.service.convertor;

import com.dh.client.PostClient;
import com.dh.client.UserClient;
import com.dh.domain.FollowNotification;
import com.dh.domain.User;
import com.dh.service.dto.ConvertedFollowNotification;
import org.springframework.stereotype.Service;

@Service
public class FollowUserNotificationConvertor {

    private final UserClient userClient;

    public FollowUserNotificationConvertor(UserClient userClient, PostClient postClient) {
        this.userClient = userClient;
    }

    public ConvertedFollowNotification convert (FollowNotification notification) {
        User user = userClient.getUser(notification.getUserId());
        boolean isFollowing = userClient.getIsFollowing(notification.getUserId(), notification.getFollowerId());

        return new ConvertedFollowNotification(
                notification.getId(),
                notification.getType(),
                notification.getOccurredAt(),
                notification.getLastUpdatedAt(),
                user.getName(),
                user.getProfileImageUrl(),
                isFollowing
        );
    }
}
