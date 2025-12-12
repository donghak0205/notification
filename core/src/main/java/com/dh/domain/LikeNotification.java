package com.dh.domain;


import lombok.Getter;
import org.springframework.data.annotation.TypeAlias;

import java.time.Instant;
import java.util.List;

@Getter
@TypeAlias("LikeNotification")
public class LikeNotification extends Notification {

    private final Long postId;
    private final List<Long> likeIds;

    public LikeNotification(String id, Long userId, NotificationType type, Instant occurredAt, Instant createdAt, Instant lastUpdatedAt, Instant deleteAt, Long postId, List<Long> likeIds) {
        super(id, userId, type, occurredAt, createdAt, lastUpdatedAt, deleteAt);
        this.postId = postId;
        this.likeIds = likeIds;
    }

    public void addLiker(Long likerId, Instant occurredAt, Instant now, Instant retention){
        this.likeIds.add(likerId);      //likeIds - 좋아요를 누룬사람들의 List
        this.setOccurredAt(occurredAt);
        this.setLastUpdatedAt(now);
        this.setDeletedAt(retention);
    }

    public void removeLiker(Long userId, Instant now){
        this.likeIds.remove(userId);
        this.setLastUpdatedAt(now);

    }
}
