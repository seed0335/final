package com.hello.post.service;

import com.hello.post.dto.PostRequestDto;
import com.hello.post.dto.PostResponseDto;
import com.hello.post.dto.post3.ResponseDto;
import com.hello.post.dto.postlike.PostLikeRequestDto;
import com.hello.post.entity.Post;
import com.hello.post.entity.PostLike;
import com.hello.post.entity.User;
import com.hello.post.repository.PostLikeRepository;
import com.hello.post.repository.PostRepository;
import com.hello.post.repository.UserRepository;
import com.hello.post.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final UserRepository userRepository;

    private final PostRepository postRepository;

    private final PostLikeRepository postLikeRepository;

    // 요구사항 3번 : 전체 게시글 목록 조회 API
    public List<ResponseDto> findAllPost() {
        List<Post> postList = postRepository.findAllByOrderByCreatedAtDesc();
        List<ResponseDto> responseDtoList = new ArrayList<>();

        for (Post post : postList) {
            responseDtoList.add(new ResponseDto(post));
        }

        return responseDtoList;

    }

    // 요구사항 4번 : 게시글 작성
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

    // 요구사항 5번 : 선택한 게시글 조회 API
    public ResponseDto findByPost(Long postNumber) {
        Post post = postRepository.findById(postNumber).orElse(null);

        ResponseDto responseDto = new ResponseDto(post);
        return responseDto;
    }

    //요구사항 6번 : 선택한 게시글 수정 API
    public ResponseDto updatePost(UserDetailsImpl userDetails, Long postNumber, PostRequestDto postRequestDto) {
        String username = userDetails.getUser().getUsername();
        Post post = postRepository.findById(postNumber).orElse(null);
        String findPostUserName = post.getUser().getUsername();

        if(username.equals(findPostUserName)) {
            post.setTitle(postRequestDto.getTitle());
            post.setContent(postRequestDto.getContent());
            postRepository.save(post);
            return new ResponseDto(post);
        }
        return null;
    }

    // 7. 선택한 게시글 삭제 API
    public String deletePost(UserDetailsImpl userDetails, Long postNumber) {
        String username = userDetails.getUser().getUsername();
        Post post = postRepository.findById(postNumber).orElse(null);
        String findPostUserName = post.getUser().getUsername();

        if(username.equals(findPostUserName)) {
            postRepository.delete(post);
            return "삭제가 완료 되었습니다.";
        }

        return "삭제 권한이 없습니다.";
    }

    // 추가 요구사항 : 게시글 좋아요 api
    public String likePost(UserDetailsImpl userDetails, Long postNumber, PostLikeRequestDto postLikeRequestDto) {
        // 로그인회원이면서, 게시글에 좋아요가 없으면 생성
        // 로그인회원이면서, 게시글에 좋아요가 있으면 생성 값이 변경

        Optional<Post> findPost = postRepository.findById(postNumber);

        Optional<PostLike> byUserAndPost = postLikeRepository.findByUserAndPost(userDetails.getUser(), findPost.get());
        if(!byUserAndPost.isPresent()) {
            PostLike postLike = new PostLike(0);

            postLike.setUser(userDetails.getUser());

            Optional<Post> post = postRepository.findById(postNumber);
            postLike.setPost(post.get());
            switch (postLike.getLike()) {
                case 0:
                    postLike.setLike(1);
                    postLikeRepository.save(postLike);
                    return "좋아요";
                case 1:
                    postLike.setLike(0);
                    postLikeRepository.save(postLike);
                    return "싫어요.";
            }
        } else {
            PostLike postLike = byUserAndPost.get();
            switch (postLike.getLike()) {
                case 0:
                    postLike.setLike(1);
                    postLikeRepository.save(postLike);
                    return "좋아요";
                case 1:
                    postLike.setLike(0);
                    postLikeRepository.save(postLike);
                    return "싫어요.";
            }
        }



        return "해당 요청을 수행 할 수 없습니다.";
    }





}
