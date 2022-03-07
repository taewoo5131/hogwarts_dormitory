package com.api.project.join.serviceImpl;

import com.api.project.join.dto.StudentDto;
import com.api.project.join.mapper.JoinMapper;
import com.api.project.join.service.JoinService;
import com.api.project.login.security.SHA256;
import com.api.project.result.ResultEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.HashMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class JoinServiceImpl implements JoinService {

    private final JoinMapper joinMapper;

    @Override
    public ResponseEntity insertStudent(StudentDto studentDto) {
        String studentId = studentDto.getStudentId();
        String studentPw = studentDto.getStudentPw();
        String studentPwCh = studentDto.getStudentPwCh();
        int dormitoryId = Integer.parseInt(studentDto.getDormitoryId());
        /**
         * Validation Check
         */
        try {
            Integer isAlreadyId = joinMapper.checkById(studentId);
            // 아이디 중복 체크
            if (isAlreadyId > 0) {
                log.info("JoinServiceImpl.insertStudent 아이디 중복 체크");
                return new ResponseEntity(ResultEnum.ALREADY_USER, HttpStatus.BAD_REQUEST);
            // 비밀번호 동일 여부
            } else if (!studentPw.equals(studentPwCh)) {
                log.info("JoinServiceImpl.insertStudent pw 동일 여부");
                return new ResponseEntity(ResultEnum.PASSWORD_ERROR, HttpStatus.BAD_REQUEST);
            // 비밀번호 length 체크
            } else if (studentPw.length() < 4) {
                log.info("JoinServiceImpl.insertStudent pw length check");
                return new ResponseEntity(ResultEnum.PASSWORD_ERROR, HttpStatus.BAD_REQUEST);
            // 기숙사 1 ~ 4 여부
            } else if (dormitoryId < 1 || dormitoryId > 4) {
                log.info("JoinServiceImpl.insertStudent 기숙사 1 ~ 4 여부");
                return new ResponseEntity(ResultEnum.ARGUMENTS_NOT_ENOUGH, HttpStatus.BAD_REQUEST);
            }
            // PW 암호화
            SHA256 sha256 = new SHA256();
            String encryptPw = sha256.encrypt(studentPw);
            studentDto.setStudentPw(encryptPw);

            // INSERT
            Integer result = joinMapper.insertStudent(studentDto);
            if (result > 0) {
                log.info("정상 회원가입 성공");
//                TransactionAspectSupport.currentTransactionStatus().
                return new ResponseEntity(ResultEnum.OK, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // rollback
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResponseEntity(ResultEnum.DB_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return null;
    }
}
