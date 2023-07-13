package com.hello.post.service;

import com.hello.post.dto.PostRequestDto;
import com.hello.post.dto.PostResponseDto;
import com.hello.post.entity.Post;
import com.hello.post.entity.User;
import com.hello.post.repository.PostRepository;
import com.hello.post.repository.UserRepository;
import com.hello.post.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    private final UserRepository userRepository;

    private final PostRepository postRepository;
    public PostResponseDto createPost(UserDetailsImpl userDetails, PostRequestDto postRequestDto) {
        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username).orElse(null);

        Post post = new Post();
        post.setTitle(postRequestDto.getTitle());
        post.setContent(postRequestDto.getContent());
        post.setUser(user);
        postRepository.save(post);

        PostResponseDto postResponseDto = new PostResponseDto(post);
        return postResponseDto;
    }
}
