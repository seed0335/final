package com.hello.post.dto.post3;

import com.hello.post.entity.Comment;
import com.hello.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ResponseDto {
    private String username;

    private String title;

    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ResponseDto(Post post) {
        this.username = post.getUser().getUsername();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }
}
