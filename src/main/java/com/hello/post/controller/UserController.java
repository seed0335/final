package com.hello.post.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    //  회원가입
    @GetMapping("/signUpPage")
    public String signUpPage() {
        return "signUpPage";
    }

    //로그인
    @GetMapping("/loginPage")
    public String loginPage() {
        return "loginPage";
    }
}
