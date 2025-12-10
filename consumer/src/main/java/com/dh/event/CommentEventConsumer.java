package com.dh.event;


import com.dh.task.CommentAddTask;
import com.dh.task.CommentRemoveTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
public class CommentEventConsumer {

    @Autowired
    CommentAddTask commentAddTask;

    @Autowired
    CommentRemoveTask commentRemoveTask;

    @Bean("comment")
    public Consumer<CommentEvent> comment(){
        //return event -> log.info(event.toString());

        return event ->{
            if( event.getType() == CommentEventType.ADD){
                System.out.println(">>> [consumer] event received. type=" + event.getType());
                commentAddTask.processEvent(event);
            } else if( event.getType() == CommentEventType.REMOVE){
                commentRemoveTask.processEvent(event);
            }
        };
    }
}
