package com.api.project.login.serviceImpl;

import com.api.project.user.serviceImpl.LoginServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class LoginServiceImplTest {

    @Autowired
    LoginServiceImpl loginService;

    @Test
    void 로그인테스트() {
//        LoginServiceImpl loginService = new LoginServiceImpl();
        Map<String, String> map = new HashMap<>();
        map.put("studentId", "harry");
        map.put("studentPw", "1234");
        loginService.login(map);
    }



}