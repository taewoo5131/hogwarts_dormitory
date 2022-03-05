package com.api.project.join.controller;

import com.api.project.join.dto.StudentDto;
import com.api.project.join.service.JoinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/join")
@RequiredArgsConstructor
@Slf4j
public class JoinController {

    private final JoinService joinService;

    /**
     * desc 회원가입 진행
     */
    @PostMapping
    public void postJoin(@RequestBody StudentDto studentDto) {
        log.info("joinController 호출");
        System.out.println(studentDto.toString());
//        joinService.insertStudent();
    }
}
