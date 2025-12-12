package com.dh.response;

import com.dh.domain.NotificationType;
import com.dh.service.dto.ConvertedFollowNotification;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.Instant;

@Getter
@Schema(description = "팔로우 알림 응답")
public class FollowUserNotificationResponse extends UserNotificationResponse {

    @Schema(description = "팔로우 알림 응답")
    private final String userName;

    @Schema(description = "팔로우 사용자 프로필 이미지")
    private final String userProfileImageUrl;

    @Schema(description = "팔로우 여부")
    private final boolean isFollowing;

    public FollowUserNotificationResponse(String id, NotificationType type, Instant occurredAt, String userName,
                                          String userProfileImageUrl, boolean isFollowing) {
        super(id, type, occurredAt);
        this.userName = userName;
        this.userProfileImageUrl = userProfileImageUrl;
        this.isFollowing = isFollowing;
    }

    public static FollowUserNotificationResponse of(ConvertedFollowNotification notification) {
        return new FollowUserNotificationResponse(
                notification.getId(),
                notification.getType(),
                notification.getOccurredAt(),
                notification.getUserName(),
                notification.getUserProfileImageUrl(),
                notification.isFollowing()
        );
    }
}
