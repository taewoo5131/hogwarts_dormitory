package com.api.project.board.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping("/board")
public class BoardController {
    @GetMapping("/list")
    public void list(HttpServletRequest request) {

    }



    @GetMapping("logout")
    public void test(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                System.out.println("get logout test " + cookie.getValue());
            }
        }
    }
}
