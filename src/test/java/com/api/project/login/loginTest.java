package com.api.project.login;

import com.api.project.login.mapper.LoginMapper;
import com.api.project.login.service.LoginService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
public class loginTest {
    @Autowired
    private LoginService loginService;

    @Autowired
    private LoginMapper loginMapper;


    @Test
    public void 로그인테스트() {
        //given
        HashMap<String, Object> admin = new HashMap<String, Object>();
        admin.put("userId", "admin");
        admin.put("userPw", 1234);
        //when
        Integer result = loginService.login(admin);
//        int result = loginMapper.login(admin);
        //then
        Assertions.assertThat(result).isEqualTo(1);
    }
}
