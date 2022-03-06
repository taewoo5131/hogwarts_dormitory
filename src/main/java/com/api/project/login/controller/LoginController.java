package com.api.project.login.controller;

import com.api.project.login.dto.TokenDto;
import com.api.project.login.security.SHA256;
import com.api.project.login.service.LoginService;
import com.api.project.result.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;
//    private final TokenDto tokenDto;

    @GetMapping
    public void getLogin() {
        log.info("getLogin controller");

    }

    @PostMapping
    public ResponseEntity<TokenDto> postLogin(@RequestBody HashMap<String,Object> paramMap) throws NoSuchAlgorithmException {
        int result = loginService.postLogin(paramMap);
        log.info("postLogin controller result {} " , result);
//        if (result > -1) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(TokenDto
                            .builder()
                            .resultCode("0000")
                            .resultMsg("성공")
                            .tokenInfo("12341234----")
                            .build());

//        }
//        return null;
    }
}
