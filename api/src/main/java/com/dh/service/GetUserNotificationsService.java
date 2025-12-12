package com.dh.service;

import com.dh.domain.CommentNotification;
import com.dh.domain.FollowNotification;
import com.dh.domain.LikeNotification;
import com.dh.service.convertor.CommentUserNotificationConvertor;
import com.dh.service.convertor.FollowUserNotificationConvertor;
import com.dh.service.convertor.LikeUserNotificationConvertor;
import com.dh.service.dto.ConvertedNotification;
import com.dh.service.dto.GetUserNotificationsByPivotResult;
import com.dh.service.dto.GetUserNotificationsResult;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class GetUserNotificationsService {

    private final NotificationListService listService;
    private final CommentUserNotificationConvertor commentConvertor;
    private final LikeUserNotificationConvertor likeConvertor;
    private final FollowUserNotificationConvertor followConvertor;

    public GetUserNotificationsService(NotificationListService listService, CommentUserNotificationConvertor commentConvertor, LikeUserNotificationConvertor likeConvertor, FollowUserNotificationConvertor followConvertor) {
        this.listService = listService;
        this.commentConvertor = commentConvertor;
        this.likeConvertor = likeConvertor;
        this.followConvertor = followConvertor;
    }

    public GetUserNotificationsResult getUserNotificationsByPivot(long userId, Instant pivot){

        //1. 알림 목록 조회
        GetUserNotificationsByPivotResult result = listService.getUserNotificationsByPivot(userId, pivot);
        List<ConvertedNotification> convertedNotifications = result.getNotifications().stream()
                .map(notification -> switch (notification.getType()){
                    case COMMENT -> commentConvertor.convert((CommentNotification) notification);
                    case LIKE -> likeConvertor.convert((LikeNotification) notification);
                    case FOLLOW -> followConvertor.convert((FollowNotification) notification);
                }).toList();
        //2. 알림목록을 순회하면서 디비 알림 -> 사용자 알람으로 변환

        return new GetUserNotificationsResult(
                convertedNotifications,
                result.isHasNext()
        );

    }

}
