package com.dh.service.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetUserNotificationsResult {
    private List<ConvertedNotification> notifications;
    private boolean hasNext;
}
