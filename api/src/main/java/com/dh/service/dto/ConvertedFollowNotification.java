package com.dh.service.dto;

import com.dh.domain.NotificationType;
import lombok.Getter;

import java.time.Instant;

@Getter
public class ConvertedFollowNotification extends ConvertedNotification {

    private final String userName;
    private final String userProfileImageUrl;
    private final boolean isFollowing;

    public ConvertedFollowNotification(String id, NotificationType type, Instant occurredAt, Instant lastUpdatedAt,
                                       String userName, String userProfileImageUrl, boolean isFollowing) {
        super(id, type, occurredAt, lastUpdatedAt);
        this.userName = userName;
        this.userProfileImageUrl = userProfileImageUrl;
        this.isFollowing = isFollowing;
    }


}
