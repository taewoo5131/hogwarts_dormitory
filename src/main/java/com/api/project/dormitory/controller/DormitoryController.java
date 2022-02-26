package com.api.project.dormitory.controller;

import com.api.project.dormitory.dto.DormitoryQuestionDto;
import com.api.project.dormitory.service.DormitoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/dormitory")
public class DormitoryController {

    private final DormitoryService dormitoryService;

    @GetMapping("/")
    public void dormitorySelect() {
        log.info("dormitory select");
        dormitoryService.selectList();
    }

    @PostMapping("/")
    public void dormitorySelectFinish() {
        log.info("dormitory selectFinish");
    }
}
