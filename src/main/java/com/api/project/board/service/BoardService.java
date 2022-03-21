package com.api.project.board.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface BoardService {
    ResponseEntity getList(String pageNo);

    ResponseEntity getBoard(String boardNo);

    ResponseEntity insertBoard(Map<String, String> paramMap);
}
