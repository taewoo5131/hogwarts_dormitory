package com.api.project.dormitory.dto;

import lombok.Data;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Data
public class DormitoryQuestionListResponseDto {
    private String dormitoryQuestionId;
    private String dormitoryQuestionTitle;
    private List<DormitoryQuestionChoiceDto> dormitoryQuestionChoiceList;
}
