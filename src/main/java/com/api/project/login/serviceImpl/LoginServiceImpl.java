package com.api.project.login.serviceImpl;

import com.api.project.login.dto.LoginStudentDto;
import com.api.project.login.mapper.LoginMapper;
import com.api.project.login.service.LoginService;
import com.api.project.security.SHA256;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
//@RequiredArgsConstructor
@Primary
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public ResponseEntity login(@RequestBody Map<String, String> paramMap) {
        paramMap = new ConcurrentHashMap<>();
        String studentId = paramMap.get("studentId");
        String studentPw = paramMap.get("studentPw");
        // validation check
        try {
            System.out.println(studentPw);
            SHA256 sha256 = new SHA256();
            String studentPwEnc = sha256.encrypt(studentPw);
            paramMap.put("studentPw", studentPwEnc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(paramMap.toString());


        LoginStudentDto responseStudent = loginMapper.login(paramMap);

        return new ResponseEntity(HttpStatus.OK);
    }
}
