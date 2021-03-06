package com.api.project.login;

import com.api.project.user.mapper.UserMapper;
import com.api.project.user.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

@ExtendWith(MockitoExtension.class)
public class loginTest {
    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserMapper loginMapper;

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
