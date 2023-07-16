package com.hello.post.service;

import com.hello.post.dto.comment.CommentRequestDto;
import com.hello.post.dto.comment.CommentResponseDto;
import com.hello.post.entity.Comment;
import com.hello.post.entity.Post;
import com.hello.post.repository.CommentRepository;
import com.hello.post.repository.PostRepository;
import com.hello.post.repository.UserRepository;
import com.hello.post.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;


    // 댓글 작성
    public CommentResponseDto createComment(UserDetailsImpl userDetails, Long postNumber, CommentRequestDto commentRequestDto) {
        Optional<Post> findPost = postRepository.findById(postNumber);


        if (findPost.isPresent()) {
            Comment createComment = new Comment();
            createComment.setUser(userDetails.getUser());
            createComment.setPost(findPost.get());
            createComment.setComment(commentRequestDto.getComment());

            commentRepository.save(createComment);
            return new CommentResponseDto(createComment);
        }
        return null;
    }

    // 댓글 수정
    public CommentResponseDto updateComment(UserDetailsImpl userDetails, Long commentNumber, CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(commentNumber).orElse(null);
        String findCommentUsername = comment.getUser().getUsername();
        String LoginUsername = userDetails.getUsername();

        if (LoginUsername.equals(findCommentUsername)) {
            comment.setComment(commentRequestDto.getComment());
            commentRepository.save(comment);
            return new CommentResponseDto(commentRequestDto.getComment());
        } else {
            return null;
        }

    }

    // 댓글 삭제
    public String DeleteComment(UserDetailsImpl userDetails, Long commentNumber) {
        Comment findComment = commentRepository.findById(commentNumber).orElse(null);
        String LoginUsername = userDetails.getUsername();
        String commentUsername = findComment.getUser().getUsername();

        if(LoginUsername.equals(commentUsername)) {
            commentRepository.deleteById(commentNumber);
            return "게시글이 삭제 되었습니다.";
        } else {
           return  "댓글 삭제 권한이 없습니다.";
        }

    }
}
