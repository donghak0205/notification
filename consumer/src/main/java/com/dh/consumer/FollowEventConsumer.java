package com.dh.consumer;


import com.dh.event.FollowEvent;
import com.dh.task.FollowAddTask;
import com.dh.task.FollowRemoveTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

import static com.dh.event.FollowEventType.ADD;
import static com.dh.event.FollowEventType.REMOVE;

@Slf4j
@Component
public class FollowEventConsumer {

    private final FollowAddTask followAddTask;

    private final FollowRemoveTask followRemoveTask;

    public FollowEventConsumer(FollowAddTask followAddTask, FollowRemoveTask followRemoveTask) {
        this.followAddTask = followAddTask;
        this.followRemoveTask = followRemoveTask;
    }

    @Bean("follow")
    public Consumer<FollowEvent> follow(){
        //return event -> log.info (event.toString());
        return event -> {
            if(event.getType() == ADD){
                followAddTask.processEvent(event);
            } else if (event.getType() == REMOVE){
                followRemoveTask.processEvent(event);
            }
        };
    }
}
