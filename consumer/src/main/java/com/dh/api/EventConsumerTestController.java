package com.dh.api;

import com.dh.event.CommentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Consumer;

@RestController
public class EventConsumerTestController implements EventConsumerTestControllerSpec {

    @Autowired
    private Consumer<CommentEvent> comment;

    @Override
    @PostMapping("/test/comment")
    public void comment(@RequestBody CommentEvent event){
        System.out.println(">>> controller called. event = " + event);
        comment.accept(event);
    }
}
