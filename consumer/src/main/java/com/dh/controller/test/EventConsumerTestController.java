package com.dh.controller.test;

import com.dh.event.CommentEvent;
import com.dh.event.FollowEvent;
import com.dh.event.LikeEvent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Consumer;

@RestController
public class EventConsumerTestController implements EventConsumerTestControllerSpec {

    private final Consumer<CommentEvent> comment;

    private final Consumer<LikeEvent> like;

    private final Consumer<FollowEvent> follow;

    public EventConsumerTestController(Consumer<CommentEvent> comment, Consumer<LikeEvent> like, Consumer<FollowEvent> follow) {
        this.comment = comment;
        this.like = like;
        this.follow = follow;
    }

    @Override
    @PostMapping("/test/comment")
    public void comment(@RequestBody CommentEvent event){
        comment.accept(event);
    }

    @Override
    @PostMapping("/test/like")
    public void like(@RequestBody LikeEvent event){
        like.accept(event);
    }

    @Override
    @PostMapping("/test/follow")
    public void follow(@RequestBody FollowEvent event) {
        follow.accept(event);
    }
}
