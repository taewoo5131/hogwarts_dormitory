package com.api.project.board.dto;

import lombok.Data;

import java.util.List;

@Data
public class BoardList {
    private String resultCode;
    private String resultMsg;
    private List<Board> boardList;
}
