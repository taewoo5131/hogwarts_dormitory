package com.api.project.dormitory.service;


import com.api.project.dormitory.dto.DormitoryQuestionDto;
import com.api.project.dormitory.dto.DormitoryQuestionResultRequestDto;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DormitoryService {
    ResponseEntity getList();

    ResponseEntity postList(HttpServletRequest req, List<DormitoryQuestionResultRequestDto> list);
}
