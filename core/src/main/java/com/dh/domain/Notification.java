package com.dh.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.Instant;

//enum NotificationType {
//    LIKE,
//    COMMENT,
//    FOLLOW
//}

@Setter
@Getter
@AllArgsConstructor
@Document("notifications")
public abstract class Notification {

    @Field(targetType = FieldType.STRING)
    public String id;       //ObjectId('123) -> "123"
    public Long userId;
    public NotificationType type;

    public Instant occurredAt; //알림 대상인 실제 이벤트가 발생한 시간
    public Instant createdAt;

    public Instant lastUpdatedAt;
    public Instant deleteAt;  //알림이 삭제될 시간

}
