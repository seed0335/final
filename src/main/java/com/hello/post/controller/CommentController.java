package com.hello.post.controller;

import com.hello.post.dto.comment.CommentRequestDto;
import com.hello.post.dto.comment.CommentResponseDto;
import com.hello.post.entity.Comment;
import com.hello.post.security.UserDetailsImpl;
import com.hello.post.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/post/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postNumber}")
    public ResponseEntity<CommentResponseDto> createComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postNumber, @RequestBody CommentRequestDto commentRequestDto) {

        CommentResponseDto createComment = commentService.createComment(userDetails, postNumber, commentRequestDto);
        if(createComment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(createComment, HttpStatus.OK);
    }

//    @PatchMapping("/{postNumber}")
//    public ResponseEntity<CommentResponseDto> updateComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postNumber, @RequestBody CommentRequestDto commentRequestDto) {
//
//        CommentResponseDto createComment = commentService.updateComment(userDetails, postNumber, commentRequestDto);
//        if(createComment == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(createComment, HttpStatus.OK);
//    }
}
