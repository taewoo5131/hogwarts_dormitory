package com.api.project.dormitory.serviceImpl;

import com.api.project.dormitory.dto.*;
import com.api.project.dormitory.mapper.DormitoryMapper;
import com.api.project.dormitory.service.DormitoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

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
            dormitoryQuestionListResponseDto.setDormitoryQuestionId(result.get(i).getDormitoryQuestionId());
            dormitoryQuestionListResponseDto.setDormitoryQuestionTitle(result.get(i).getDormitoryQuestionTitle());
            questionChoiceList  = new ArrayList<>();
            for (int j = 0; j < result2.size(); j++) { // 30
                if (result2.get(j).getDormitoryQuestionId().equals(result.get(i).getDormitoryQuestionId())) {
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
    public ResponseEntity postList(HttpServletRequest req , List<DormitoryQuestionResultRequestDto> list) {
        if (list.size() == dormitoryMapper.questionList().size()) {
            String dormitoryName = getDormitoryName(list);
            DormitoryInfoDto dormitoryInfo = dormitoryMapper.getDormitoryInfo(dormitoryName);
            log.info("dormitory info => {} " , dormitoryInfo);
            HttpSession session = req.getSession();
            session.setAttribute("dormitoryName", dormitoryInfo.getDormitoryId());
            return new ResponseEntity(dormitoryInfo, HttpStatus.OK);

        }else{
            log.error("Insufficient answers submitted => {} " , list.size());
        }
        return null;
    }

    /**
     * desc 기숙사 배정 question의 응답에서 배정받을 기숙사 이름 return
     * @return DormitoryName
     */
    public String getDormitoryName(List<DormitoryQuestionResultRequestDto> paramList) {
        AtomicReference<String> dormitoryName = new AtomicReference<>("");
        List<String> resultList = new ArrayList<String>();
        Map<String, String> argsMap;
        for (int i = 0; i < paramList.size(); i++) {
            argsMap = new HashMap<>();
            argsMap.put("questionId",paramList.get(i).getDormitoryQuestionId());
            argsMap.put("questionAnswer",paramList.get(i).getDormitoryQuestionAnswerId());
            String result = dormitoryMapper.questionResult(argsMap);
            resultList.add(result);
        }
        Stream<String> stringStream = resultList.stream().filter(i -> Collections.frequency(resultList, i) > 1);
        stringStream.sorted().forEach(item -> dormitoryName.set(item));
        return dormitoryName.get();
    }
}
