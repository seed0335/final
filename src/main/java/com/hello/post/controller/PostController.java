package com.hello.post.controller;

import com.hello.post.dto.PostRequestDto;
import com.hello.post.dto.PostResponseDto;
import com.hello.post.dto.post3.ResponseDto;
import com.hello.post.entity.User;
import com.hello.post.security.UserDetailsImpl;
import com.hello.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {

    /**
     * 4. 인증(Authentication)에서 UserDetails 값 받아오기
     * Authentication -> getPrincipal() -> UserDetails -> user
     */

    
    private final PostService postService;
    
    // 전체 게시글 목록 조회
    @GetMapping
    public List<ResponseDto> findAllPost() {
        log.info("전체 게시글 목록 조회");
        List<ResponseDto> allPost = postService.findAllPost();
        return allPost;
    }

    // 게시글 등록
    @ResponseBody
    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PostRequestDto postRequestDto) {
        log.info("postRequestDto={}", postRequestDto);
        PostResponseDto post = postService.createPost(userDetails, postRequestDto);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
}
