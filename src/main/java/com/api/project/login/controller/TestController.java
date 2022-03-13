package com.api.project.login.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/test")
public class TestController {
    public void test(HttpServletRequest request) {
        String access_token = request.getHeader("ACCESS_TOKEN");
        System.out.println(access_token);
    }
}
