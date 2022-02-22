package com.api.project.login.controller;

import com.api.project.login.dto.LoginDto;
import com.api.project.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/test")
    public void login(@RequestBody HashMap<String,Object> paramMap) {
        int result = loginService.login(paramMap);
        log.info("login ~~~ {} " , result);
    }
}
