package com.api.project.board.service;

import org.springframework.http.ResponseEntity;

public interface BoardService {
    ResponseEntity getList(String pageNo);
}
