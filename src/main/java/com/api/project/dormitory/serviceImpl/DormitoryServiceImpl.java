package com.api.project.dormitory.serviceImpl;

import com.api.project.dormitory.dto.DormitoryQuestionChoiceDto;
import com.api.project.dormitory.dto.DormitoryQuestionDto;
import com.api.project.dormitory.mapper.DormitoryMapper;
import com.api.project.dormitory.service.DormitoryService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class DormitoryServiceImpl implements DormitoryService {

    private final DormitoryMapper dormitoryMapper;
    /**
     * desc 문제 리스트와 해당 문제 선택지 리스트 SELECT
     * @return
     */
    @Override
    public void selectList() {
        List<DormitoryQuestionDto> result = dormitoryMapper.questionList();
        // QUESTION LIST SELECT
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i).getDORMITORY_QUESTION_ID());
            System.out.println(result.get(i).getDORMITORY_QUESTION_TITLE());
        }



        // QUESTION CHOICE LIST SELECT
       /* List<DormitoryQuestionChoiceDto> result2 = dormitoryMapper.questionChoiceList();
        result2.forEach(item -> {
            log.info("item == {}" , item);
        });*/
    }
}
