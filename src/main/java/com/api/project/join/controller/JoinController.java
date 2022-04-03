package com.api.project.join.controller;

import com.api.project.join.dto.StudentDto;
import com.api.project.join.service.JoinService;
import com.api.project.result.ResultEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/join")
@RequiredArgsConstructor
@Slf4j
public class JoinController {

    private final JoinService joinService;

    /**
     * 회원가입 진행
     * : @Valid로 클라이언트에서 넘어온 파라미터인 studentDto를 체크하는데 기본적으로 NotNull 등등에 걸리게 되면
     *   result 가 Error를 담고 넘어온다. result.hasError()가 있을때는 ResponseEntity에 상태코드 , 상태 메시지 담아서 return
     */
    @PostMapping
    public ResponseEntity postJoin(@Valid StudentDto studentDto, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            List<ObjectError> list =  result.getAllErrors();
            for(ObjectError e : list) {
                log.error("[JoinController] [postJoin] > {}, ", e.getDefaultMessage());
            }
            return new ResponseEntity(ResultEnum.ARGUMENTS_NOT_ENOUGH, HttpStatus.BAD_REQUEST);
        }
        ResponseEntity response = joinService.insertStudent(studentDto , request);
        return response;
    }
}
