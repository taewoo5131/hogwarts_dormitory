package com.api.project.board.controller;

import com.api.project.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /**
     * 게시글 리스트 조회
     */
    @GetMapping
    public void getList(@RequestParam String pageNo,HttpServletRequest request) {
        System.out.println("getList  "+pageNo);
        boardService.getList(pageNo);

    }

    /**
     * 게시글 단건 조회
     */
    @GetMapping("/{boardNo}")
    public void getBoard(@PathVariable("boardNo") String boardNo) {
        System.out.println("getBoard >> " + boardNo);
    }

    /**
     * 게시글 등록
     */
    @PostMapping
    public void postBoard() {
        System.out.println("postBoard  ");
    }

    /**
     * 게시글 수정
     */
    @PatchMapping("/{boardNo}")
    public void patchBoard(@PathVariable("boardNo") String boardNo) {
        System.out.println("patchBoard >> " + boardNo);
    }

    /**
     * 게시글 삭제
     */
    @DeleteMapping("/{boardNo}")
    public void deleteBoard(@PathVariable("boardNo") String boardNo) {
        System.out.println("deleteBoard >> " + boardNo);
    }
}
