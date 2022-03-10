package com.api.project.login.serviceImpl;

import com.api.project.login.dto.LoginStudentDto;
import com.api.project.login.mapper.LoginMapper;
import com.api.project.login.service.LoginService;
import com.api.project.result.ResultEnum;
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

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Primary
@Slf4j
public class LoginServiceImpl implements LoginService {

    private final LoginMapper loginMapper;

    @Override
    public ResponseEntity login(@RequestBody Map<String, String> paramMap) {
        String studentId = paramMap.get("studentId");
        String studentPw = paramMap.get("studentPw");

        try {
            // validation check
            if (studentId.equals("") || studentPw.equals("")) {
                return new ResponseEntity(ResultEnum.ARGUMENTS_NOT_ENOUGH, HttpStatus.BAD_REQUEST);
            }

            // 학생의 Salt를 가져옴
            String salt = loginMapper.getSalt(studentId);

            // 암호화된 PW + salt 로 로그인 검증
            SHA256 sha256 = new SHA256();
            String studentPwEnc = sha256.loginEncrypt(salt, studentPw);
            paramMap.put("studentPw", studentPwEnc);

            // DB SELECT
            LoginStudentDto loginStudentDto = loginMapper.login(paramMap);

            // JWT 토큰 발급



        } catch (NullPointerException e) {
            e.printStackTrace();
            return new ResponseEntity(ResultEnum.ARGUMENTS_NOT_ENOUGH, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(ResultEnum.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
