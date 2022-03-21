package com.api.project.board.dto;

import lombok.Data;

@Data
public class Board {
    private String studentSeqId;
    private String studentName;
    private String studentId;
    private String dormitoryId;
    private String boardSeqId;
    private String boardTitle;
    private String boardBody;
    private String fileSeqId;
    private String filePath;
    private String fileUploadDt;
    private String fileTitle;
}
