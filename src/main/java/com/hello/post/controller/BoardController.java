package com.hello.post.controller;

import com.hello.post.dto.post.PostAllResponseDto;
import com.hello.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

    private final PostService postService;

    // 요구사항 3번 : 전체 게시글 목록 조회
    @GetMapping
    public String findAll(Model model) {
        log.info("전체 게시글 목록 조회");
        List<PostAllResponseDto> allPost = postService.findAllPost();
        model.addAttribute("allPost", allPost);
        return "board";
    }
}
