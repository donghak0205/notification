package com.dh.consumer;


import com.dh.event.LikeEvent;
import com.dh.event.LikeEventType;
import com.dh.task.LikeAddTask;
import com.dh.task.LikeRemoveTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
public class LikeEventConsumer {

    @Autowired
    private LikeAddTask likeAddTask;

    @Autowired
    private LikeRemoveTask likeRemoveTask;

    @Bean("like")
    public Consumer<LikeEvent> like(){
        //return event -> log.info(event.toString());
        return event -> {
            if(event.getType() == LikeEventType.ADD){
                likeAddTask.processEvent(event);
            } else if(event.getType() == LikeEventType.REMOVE){
                likeRemoveTask.processEvent(event);

            }
        };
    }
}
