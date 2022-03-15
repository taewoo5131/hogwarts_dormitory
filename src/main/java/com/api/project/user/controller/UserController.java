package com.api.project.user.controller;

import com.api.project.user.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final LoginService loginService;

    /**
     * login
     * @param paramMap
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Map<String, String> paramMap) {
        ResponseEntity result = loginService.login(paramMap);
        return result;
    }


}
