package com.dh.controller;


import com.dh.response.SetLastReadAtResponse;
import com.dh.service.LastReadAtService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/v1/user-notifications")
public class NotificationReadController implements NotificationReadControllerSpec {

    private final LastReadAtService service;;

    public NotificationReadController(LastReadAtService service){
        this.service = service;
    }

    @Override
    @PutMapping("/{userId}/read")
    public SetLastReadAtResponse setLastReadAt(
        @PathVariable Long userId
    ){
        Instant lastReadAt = service.setLastReadAt(userId);
        return new SetLastReadAtResponse(lastReadAt);
    }
}
