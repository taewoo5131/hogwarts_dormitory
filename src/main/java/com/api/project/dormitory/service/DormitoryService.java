package com.api.project.dormitory.service;


import com.api.project.dormitory.dto.DormitoryQuestionDto;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DormitoryService {
    ResponseEntity getList();

    ResponseEntity postList(List list);
}
