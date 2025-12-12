package com.dh.controller;

import com.dh.response.UserNotificationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.Instant;


@Tag(name = "사용자 알림센터 API")
public interface UserNotificationListControllerSpec {

    @Operation(summary = "사용자 알림 목록 조회")
    UserNotificationResponse getNotifications (
            @Parameter(example = "1") Long id,
            @Parameter(example = "2025-01-01T00:11:22.382Z") Instant pivot
    );

}


