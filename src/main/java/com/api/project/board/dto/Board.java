package com.api.project.board.dto;

import lombok.Data;


/**
 * 게시글 조회 용 dto
 */
@Data
public class Board {
    private String studentSeqId;
    private String studentName;
    private String studentId;
    private String dormitoryId;
    private String boardSeqId;
    private String boardTitle;
    private String boardBody;
}
