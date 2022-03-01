package com.api.project.dormitory.mapper;

import com.api.project.dormitory.dto.DormitoryInfoDto;
import com.api.project.dormitory.dto.DormitoryQuestionChoiceDto;
import com.api.project.dormitory.dto.DormitoryQuestionDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface DormitoryMapper {
    // QUESTION LIST SELECT
    List<DormitoryQuestionDto> questionList();
    // QUESTION CHOICE LIST SELECT
    List<DormitoryQuestionChoiceDto> questionChoiceList();
    // QUESTION CHOICE RESULT SELECT
    String questionResult(Map<String,String> paramMap);
    // GET DORMITORY INFO
    DormitoryInfoDto getDormitoryInfo(String dormitoryAlias);
}
