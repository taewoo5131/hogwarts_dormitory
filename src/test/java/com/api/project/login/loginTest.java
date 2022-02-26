package com.api.project.login;

import com.api.project.login.dto.UserDto;
import com.api.project.login.mapper.LoginMapper;
import com.api.project.login.service.LoginService;
import com.api.project.login.serviceImpl.LoginServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@ExtendWith(MockitoExtension.class)
public class loginTest {
    @InjectMocks
    private LoginServiceImpl loginServiceImpl;

    @Mock
    private LoginMapper loginMapper;

    @Test
    public void 로그인테스트() {
        HashMap<String, Object> param = new HashMap<>();
        param.put("userId", "admin");
        param.put("userPw",1234);
//        Integer login = loginServiceImpl.login(param);
//        System.out.println(login);
        //given
//        HashMap<String, Object> admin = new HashMap<String, Object>();
//        admin.put("userId", "admin");
//        admin.put("userPw", 1234);
        //when
//        Integer result = loginService.login(admin);
//        int result = loginMapper.login(admin);
        //then
//        Assertions.assertThat(result).isEqualTo(1);
    }
}
