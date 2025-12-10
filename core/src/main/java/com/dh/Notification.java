package com.dh;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

//enum NotificationType {
//    LIKE,
//    COMMENT,
//    FOLLOW
//}

@Getter
@AllArgsConstructor
@Document("notifications")
public abstract class Notification {
    public String id;
    public Long userId;
    public NotificationType type;

    public Instant occurredAt; //알림 대상인 실제 이벤트가 발생한 시간
    public Instant createdAt;

    public Instant lastUpdatedAt;
    public Instant deleteAt;  //알림이 삭제될 시간

}
