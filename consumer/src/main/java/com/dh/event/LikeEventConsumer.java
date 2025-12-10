package com.dh.event;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
public class LikeEventConsumer {

    @Bean("like")
    public Consumer<LikeEvent> like(){
        return event -> log.info(event.toString());
    }
}
