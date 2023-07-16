package com.hello.post.controller;

import com.hello.post.dto.PostRequestDto;
import com.hello.post.dto.PostResponseDto;
import com.hello.post.dto.post3.ResponseDto;
import com.hello.post.dto.postlike.PostLikeRequestDto;
import com.hello.post.entity.PostLike;
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
    
    // 요구사항 3번 : 전체 게시글 목록 조회
    @GetMapping
    public List<ResponseDto> findAllPost() {
        log.info("전체 게시글 목록 조회");
        List<ResponseDto> allPost = postService.findAllPost();
        return allPost;
    }

    // 요구사항 4번 :게시글 작성
    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PostRequestDto postRequestDto) {
        log.info("postRequestDto={}", postRequestDto);
        PostResponseDto post = postService.createPost(userDetails, postRequestDto);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    // 요구사항 5번 선택한 게시글 조회
    @GetMapping("/{postNumber}")
    public ResponseEntity<ResponseDto> findByPost(@PathVariable Long postNumber) {
        ResponseDto findPost = postService.findByPost(postNumber);
        return new ResponseEntity<>(findPost, HttpStatus.OK);
    }

    //요구사항 6번 : 선택한 게시글 수정 API
    @PostMapping("/{postNumber}")
    public ResponseEntity<ResponseDto> updatePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postNumber, @RequestBody PostRequestDto postRequestDto) {
        log.info("userDetails={}", userDetails.getUsername());
        ResponseDto updatePost = postService.updatePost(userDetails, postNumber, postRequestDto);

        return new ResponseEntity<>(updatePost, HttpStatus.OK);
    }

    // 7. 선택한 게시글 삭제 API
    @DeleteMapping("/{postNumber}")
    public ResponseEntity<String> deletePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postNumber) {
        String message = postService.deletePost(userDetails, postNumber);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // 추가 요구사항 1번: 게시글 좋아요 api
    @PostMapping("/{postNumber}/likePost")
    public ResponseEntity<String> LikePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postNumber, PostLikeRequestDto postLikeRequestDto) {
        String message = postService.likePost(userDetails, postNumber, postLikeRequestDto);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

}
