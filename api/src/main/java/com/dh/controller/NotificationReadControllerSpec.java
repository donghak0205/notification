package com.dh.controller;

import com.dh.response.SetLastReadAtResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = " 사용자 알림센터 API")
public interface NotificationReadControllerSpec {

    @Operation(summary = "사용자가 알림 목록을 일은 시간 기록")
    SetLastReadAtResponse setLastReadAt(
        @Parameter(example ="1") Long userId
    );
}
