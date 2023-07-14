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


    public CommentResponseDto createComment(UserDetailsImpl userDetails, Long postNumber, CommentRequestDto commentRequestDto) {
        Optional<Post> findPost = postRepository.findById(postNumber);


        if(findPost.isPresent()) {
            Comment createComment = new Comment();
            createComment.setUser(userDetails.getUser());
            createComment.setPost(findPost.get());
            createComment.setComment(commentRequestDto.getComment());

            commentRepository.save(createComment);
            return new CommentResponseDto(createComment);
        }
        return null;
    }

}
