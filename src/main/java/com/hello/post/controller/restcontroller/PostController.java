//package com.hello.post.controller.restcontroller;
//
//import com.hello.post.dto.post.PostRequestDto;
//import com.hello.post.dto.post.PostResponseDto;
//import com.hello.post.dto.post.PostAllResponseDto;
//import com.hello.post.security.UserDetailsImpl;
//import com.hello.post.service.PostService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Slf4j
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/post")
//public class PostController {
//
//
//    private final PostService postService;
//
//
//    // 요구사항 3번 : 전체 게시글 목록 조회
//    @GetMapping
//    public List<PostAllResponseDto> findAllPost() {
//        log.info("전체 게시글 목록 조회");
//        List<PostAllResponseDto> allPost = postService.findAllPost();
//        return allPost;
//    }
//
//    // 요구사항 4번 :게시글 작성
//    @PostMapping
//    public ResponseEntity<PostResponseDto> createPost(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PostRequestDto postRequestDto) {
//        log.info("postRequestDto={}", postRequestDto);
//        PostResponseDto post = postService.createPost(userDetails, postRequestDto);
//        return new ResponseEntity<>(post, HttpStatus.OK);
//    }
//
//    // 요구사항 5번 선택한 게시글 조회
//    @GetMapping("/{postNumber}")
//    public ResponseEntity<PostAllResponseDto> findByPost(@PathVariable Long postNumber) {
//        PostAllResponseDto findPost = postService.findByPost(postNumber);
//        return new ResponseEntity<>(findPost, HttpStatus.OK);
//    }
//
//    //요구사항 6번 : 선택한 게시글 수정 API
//    @PostMapping("/{postNumber}")
//    public ResponseEntity<PostAllResponseDto> updatePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postNumber, @RequestBody PostRequestDto postRequestDto) {
//        log.info("userDetails={}", userDetails.getUsername());
//        PostAllResponseDto updatePost = postService.updatePost(userDetails, postNumber, postRequestDto);
//
//        return new ResponseEntity<>(updatePost, HttpStatus.OK);
//    }
//
//    // 7. 선택한 게시글 삭제 API
//    @DeleteMapping("/{postNumber}")
//    public ResponseEntity<String> deletePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postNumber) {
//        String message = postService.deletePost(userDetails, postNumber);
//        return new ResponseEntity<>(message, HttpStatus.OK);
//    }
//
//    // 추가 요구사항 1번: 게시글 좋아요 api
//    @PostMapping("/{postNumber}/likePost")
//    public ResponseEntity<String> LikePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postNumber) {
//        String message = postService.likePost(userDetails, postNumber);
//        return new ResponseEntity<>(message,HttpStatus.OK);
//    }
//
//}
