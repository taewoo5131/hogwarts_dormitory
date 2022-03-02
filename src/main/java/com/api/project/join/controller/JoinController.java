package com.api.project.join.controller;

import com.api.project.join.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/join")
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    /**
     * desc 회원가입을 하려면 dormitory정보가 존재해야 한다. ( 기숙사 배정을 받은 사람만 회원가입 가능 )
     */
    @GetMapping
    public void getJoin() {

    }


    /**
     * desc 회원가입 진행
     */
    @PostMapping
    public void postJoin() {

    }
}
