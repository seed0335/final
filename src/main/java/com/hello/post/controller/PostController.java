package com.hello.post.controller;

import com.hello.post.entity.User;
import com.hello.post.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {

    /**
     * 4. 인증(Authentication)에서 UserDetails 값 받아오기
     * Authentication -> getPrincipal() -> UserDetails -> user
     */

    @GetMapping("/post")
    public String findAllPost(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        User user = userDetails.getUser();
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());

        model.addAttribute("username", user.getUsername());
        return "index";
    }
}
