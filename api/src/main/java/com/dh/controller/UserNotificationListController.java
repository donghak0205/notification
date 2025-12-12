package com.dh.controller;


import com.dh.response.UserNotificationListResponse;
import com.dh.service.GetUserNotificationsService;
import com.dh.service.dto.GetUserNotificationsResult;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/v1/user-notifications")
public class UserNotificationListController {

    private final GetUserNotificationsService getUserNotificationService;

    public UserNotificationListController(GetUserNotificationsService getUserNotificationsService) {
        this.getUserNotificationService = getUserNotificationsService;
    }


    @GetMapping("/{userId}")
    public UserNotificationListResponse getNotifications(
            @PathVariable(value = "userID") Long userID,
            @RequestParam(value = "pivot", required = false) Instant pivot
    ){
        return UserNotificationListResponse.of(
                getUserNotificationService.getUserNotificationsByPivot(userID, pivot)
        );
    }

}
