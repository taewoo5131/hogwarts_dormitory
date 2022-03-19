package com.api.project.user.serviceImpl;

import com.api.project.token.JwtTokenProvider;
import com.api.project.user.dto.LoginStudentDto;
import com.api.project.user.mapper.UserMapper;
import com.api.project.user.service.UserService;
import com.api.project.result.ResultEnum;
import com.api.project.security.SHA256;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Primary
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    private final UserMapper loginMapper;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public ResponseEntity login(@RequestBody Map<String, String> paramMap, HttpServletResponse response) {
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

            // DB SELECT : 로그인이 잘못되면 NullPointerException 발생
            try {
                LoginStudentDto loginStudentDto = loginMapper.login(paramMap);
                if (loginStudentDto == null) {
                    throw new NullPointerException("select 결과 null");
                }
                // JWT 토큰 발급
                String accessToken = jwtTokenProvider.makeAccessJwtToken(loginStudentDto.getStudentSeqId());
                String refreshToken = jwtTokenProvider.makeRefreshJwtToken();

                // refreshToken DB 저장
                HashMap<String, String> changeMap = new HashMap<>();
                changeMap.put("studentId", studentId);
                changeMap.put("refreshToken", refreshToken);
                int result = loginMapper.changeRefreshToken(changeMap);

                // update 정상 처리
                if (result > 0) {
                    // Client에게 Dto return
                    loginStudentDto.setResultCode(ResultEnum.OK.getResultCode());
                    loginStudentDto.setResultMsg(ResultEnum.OK.getResultMsg());
                    loginStudentDto.setToken(accessToken);
                    return new ResponseEntity(loginStudentDto, HttpStatus.OK);
                }
            // select 정보가 없을때
            } catch (NullPointerException e) {
                log.error("login 실패 : client 에러 -> {} ", e.getMessage());
                return new ResponseEntity(ResultEnum.LOGIN_ERROR, HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                log.error("login 실패 : API 서버 에러 -> {}" , e);
                return new ResponseEntity(ResultEnum.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        // 파라미터 null 일때
        } catch (NullPointerException e) {
            e.printStackTrace();
            return new ResponseEntity(ResultEnum.ARGUMENTS_NOT_ENOUGH, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(ResultEnum.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }

    @Override
    public ResponseEntity logout(Map<String, String> paramMap) {
        System.out.println(paramMap.toString());
        String token = paramMap.get("token");
        String studentId = paramMap.get("studentId");
        String dormitoryId = paramMap.get("dormitoryId");

        return null;
    }
}
