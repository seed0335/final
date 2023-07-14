package com.hello.post.dto.comment;

import com.hello.post.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {

    private String comment;

    public CommentResponseDto(Comment createComment) {
        this.comment = createComment.getComment();
    }
}
