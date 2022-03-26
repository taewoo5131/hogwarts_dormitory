package com.api.project.dormitory.controller;

import com.api.project.dormitory.dto.DormitoryQuestionDto;
import com.api.project.dormitory.dto.DormitoryQuestionResultRequestDto;
import com.api.project.dormitory.service.DormitoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/dormitory")
@Api("Dormitory Controller API")
public class DormitoryController {

    private final DormitoryService dormitoryService;

    @GetMapping
    public ResponseEntity getDormitorySelect() {
        ResponseEntity response = dormitoryService.getList();
        return response;
    }

    @PostMapping
    public ResponseEntity postDormitorySelect(HttpServletRequest req , @RequestBody List<DormitoryQuestionResultRequestDto> list){
        ResponseEntity response = dormitoryService.postList(req, list);
        log.info("[DormitoryController] [postDormitorySelect] > {} ", response.toString());
        return response;
    }
}
