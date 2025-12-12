package com.dh.response;

import com.dh.domain.NotificationType;
import com.dh.service.dto.ConvertedCommentNotification;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.Instant;

@Getter
@Schema(description = "댓글 알림응답")
public class CommentUserNotificationResponse extends UserNotificationResponse {

    @Schema(description = "댓글을 남긴 사용자 이름")
    private final String userName;

    @Schema(description = "댓글을 남긴 사용자 프로필 이미지")
    private final String userProfileImage;

    @Schema(description = "댓글 내용")
    private final String comment;

    @Schema(description = "게시글 이미지")
    private final String postImageUrl;

    public CommentUserNotificationResponse(String id, NotificationType type, Instant occurredAt, String userName,
                                           String userProfileImage, String comment, String postImageUrl) {
        super(id, type, occurredAt);
        this.userName = userName;
        this.userProfileImage = userProfileImage;
        this.comment = comment;
        this.postImageUrl = postImageUrl;
    }

    public static CommentUserNotificationResponse of(ConvertedCommentNotification notification) {
        return new CommentUserNotificationResponse(
                notification.getId(),
                notification.getType(),
                notification.getOccurredAt(),
                notification.getUserName(),
                notification.getUserProfileImageUrl(),
                notification.getComment(),
                notification.getPostImageUrl()
        );
    }
}
