package com.api.project.login.serviceImpl;

import com.api.project.login.mapper.LoginMapper;
import com.api.project.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginServiceImpl implements LoginService {
    private final LoginMapper loginMapper;

    @Override
    public Integer login(HashMap<String, Object> paramMap) {
        if (!paramMap.isEmpty()) {
            // validation check !!!!

            // mapper call
            int result = loginMapper.login(paramMap);
            log.info("service ~~~ {} ", result);
            if (result > -1) {
                return result;
            }
        }
        return null;
    }
}
