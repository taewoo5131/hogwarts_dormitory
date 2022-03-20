package com.api.project.board.serviceImpl;

import com.api.project.board.mapper.BoardMapper;
import com.api.project.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    private int pageSize = 3;

    @Override
    public ResponseEntity getList(String pageNoParam) {
        int pageNo = Integer.parseInt(pageNoParam);
        /**
         * 전체 게시글 개수 반환
         */
        int allCount = boardMapper.getAllCount();

        /**
         * 전체 게시글
         */
        Map<String, Integer> paramMap = new HashMap<>();
        // limit 0,10
        if (pageNo == 1) {
            paramMap.put("pageNo", 0);                // 0  // offset
            paramMap.put("pageSize", this.pageSize);    // 10 // limit
        // limit
        }else{
            paramMap.put("pageNo", (this.pageSize * pageNo - this.pageSize) );
            paramMap.put("pageSize", this.pageSize);
        }

//        ArrayList board = boardMapper.getBoard(paramMap);
//        System.out.println(boardMapper.getBoard(paramMap));
        System.out.println(boardMapper.getBoard());


        return null;
    }
}
