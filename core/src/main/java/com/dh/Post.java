package com.dh;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Post {
    //가상의 Post 서버 API구현
    private Long id;
    private Long userId;

    private String imageUrl;
    private String content;
}
