package com.api.project.dormitory.serviceImpl;

import com.api.project.dormitory.dto.DormitoryQuestionChoiceDto;
import com.api.project.dormitory.dto.DormitoryQuestionDto;
import com.api.project.dormitory.dto.DormitoryQuestionListResponseDto;
import com.api.project.dormitory.dto.DormitoryQuestionResultRequestDto;
import com.api.project.dormitory.mapper.DormitoryMapper;
import com.api.project.dormitory.service.DormitoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public ResponseEntity getList() {
        List<DormitoryQuestionListResponseDto> questionList = new ArrayList();
        List<DormitoryQuestionChoiceDto> questionChoiceList;

        // QUESTION LIST SELECT
        List<DormitoryQuestionDto> result = dormitoryMapper.questionList();
        // QUESTION CHOICE LIST SELECT
        List<DormitoryQuestionChoiceDto> result2 = dormitoryMapper.questionChoiceList();

        if (result.size() < 0 || result2.size() < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        DormitoryQuestionListResponseDto dormitoryQuestionListResponseDto;
        for (int i = 0; i < result.size(); i++) { // 6
            dormitoryQuestionListResponseDto = new DormitoryQuestionListResponseDto();
            dormitoryQuestionListResponseDto.setDormitoryQuestionId(result.get(i).getDORMITORY_QUESTION_ID());
            dormitoryQuestionListResponseDto.setDormitoryQuestionTitle(result.get(i).getDORMITORY_QUESTION_TITLE());
            questionChoiceList  = new ArrayList<>();
            for (int j = 0; j < result2.size(); j++) { // 30
                if (result2.get(j).getDORMITORY_QUESTION_ID().equals(result.get(i).getDORMITORY_QUESTION_ID())) {
                    questionChoiceList.add(result2.get(j));
                    dormitoryQuestionListResponseDto.setDormitoryQuestionChoiceList(questionChoiceList);
                }
            }
            questionList.add(dormitoryQuestionListResponseDto);
        }
        log.info("result {} ", questionList);
        return new ResponseEntity(questionList,HttpStatus.OK);
    }

    /**
     * desc 문제 리스트와 해당 문제 선택지 리스트 post 전송
     * @return
     */
    @Override
    public ResponseEntity postList(List<DormitoryQuestionResultRequestDto> list) {
        if (list.size() == dormitoryMapper.questionList().size()) {
            getDormitoryName(list);
        }else{
            log.error("error choice answer is not empty => {} " , list.size());
        }
        return null;
    }

    /**
     * desc 기숙사 배정 결과 GET Method
     * @return DormitoryName
     */
    public String getDormitoryName(List<DormitoryQuestionResultRequestDto> paramList) {
        String dormitoryName = "";
        String[] dormitoryArr = new String[paramList.size()];
        paramList.forEach(item -> {
            switch (Integer.parseInt(item.getDormitoryQuestionId())){
                case 1:
                    log.info("switch case 1 >>  {} ", item.getDormitoryQuestionId());
                    break;
                case 2:
                    log.info("switch case 2 >>  {} ", item.getDormitoryQuestionId());
                    break;
                case 3:
                    log.info("switch case 3 >>  {} ", item.getDormitoryQuestionId());
                    break;
                case 4:
                    log.info("switch case 4 >>  {} ", item.getDormitoryQuestionId());
                    break;
                case 5:
                    log.info("switch case 5 >>  {} ", item.getDormitoryQuestionId());
                    break;
                case 6:
                    log.info("switch case 6 >>  {} ", item.getDormitoryQuestionId());
                    break;
            }

        });

        return dormitoryName;
    }
}
