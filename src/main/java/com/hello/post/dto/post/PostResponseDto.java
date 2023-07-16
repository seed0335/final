package com.hello.post.dto.post;

import com.hello.post.entity.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponseDto {

    private String username;
    private String title;
    private String content;

    public PostResponseDto(Post post) {
        this.username = post.getUser().getUsername();
        this.title = post.getTitle();
        this.content = post.getContent();
    }
}
