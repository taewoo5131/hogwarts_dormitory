package com.api.project.board.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class BoardSelect {
    private String resultCode;
    private String resultMsg;
    private String studentSeqId;
    private String studentName;
    private String studentId;
    private String dormitoryId;
    private String boardSeqId;
    private String boardTitle;
    private String boardBody;
    private List<Map<String, Object>> fileList;
}
