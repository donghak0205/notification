package com.dh.event;


import lombok.Data;

@Data
public class CommentEvent {
    private CommentEventType type;
    private Long postId;
    private Long userId;
    private Long commentId;
}
