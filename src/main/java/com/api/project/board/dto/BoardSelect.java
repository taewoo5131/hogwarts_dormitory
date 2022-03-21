package com.api.project.board.dto;

import lombok.Data;

@Data
public class BoardSelect {
    private String resultCode;
    private String resultMsg;
    private Board board;
}
