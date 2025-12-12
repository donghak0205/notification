package com.dh.service.dto;

import com.dh.domain.NotificationType;
import lombok.Getter;

import java.time.Instant;

@Getter
public class ConvertedCommentNotification extends ConvertedNotification {

    private final String userName;
    private final String userProfileImageUrl;
    private final String comment;
    private final String postImageUrl;

    public ConvertedCommentNotification(String id, NotificationType type, Instant occurredAt, Instant lastUpdatedAt,
                                        String userName, String userProfileImageUrl, String comment, String postImageUrl) {
        super(id, type, occurredAt, lastUpdatedAt);
        this.userName = userName;
        this.userProfileImageUrl = userProfileImageUrl;
        this.comment = comment;
        this.postImageUrl = postImageUrl;
    }

}
