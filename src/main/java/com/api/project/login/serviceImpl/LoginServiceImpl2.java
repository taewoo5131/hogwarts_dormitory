package com.api.project.login.serviceImpl;

import com.api.project.login.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Slf4j
public class LoginServiceImpl2{
    public Integer login(HashMap<String, Object> paramMap) {
        log.info("이거아닌데");
        return null;
    }
}
