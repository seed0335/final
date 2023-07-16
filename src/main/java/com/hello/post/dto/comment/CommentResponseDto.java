package com.hello.post.dto.comment;

import com.hello.post.entity.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDto {

    private String comment;

    public CommentResponseDto(Comment createComment) {
        this.comment = createComment.getComment();
    }

    public CommentResponseDto(String comment) {
        this.comment = comment;
    }
}
