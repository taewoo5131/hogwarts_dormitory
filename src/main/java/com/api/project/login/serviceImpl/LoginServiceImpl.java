package com.api.project.login.serviceImpl;

import com.api.project.login.dto.UserDto;
import com.api.project.login.mapper.LoginMapper;
import com.api.project.login.security.SHA256;
import com.api.project.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
@Slf4j
@Primary
public class LoginServiceImpl implements LoginService {
    private final LoginMapper loginMapper;

    @Override
    public Integer postLogin(HashMap<String, Object> paramMap){
        if (!paramMap.isEmpty()) {
            // 암호화
            try {
                SHA256 sha256 = new SHA256();
                String encrypt = sha256.encrypt(String.valueOf(paramMap.get("studentPw")));
                paramMap.put("studentPw", encrypt);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // validation check !!!!


            // mapper call
            log.info(paramMap.toString());
            int result = loginMapper.login(paramMap);
            log.info("service ~~~ {} ", result);
            if (result > -1) {
                return result;
            }
        }
        return null;
    }
}
