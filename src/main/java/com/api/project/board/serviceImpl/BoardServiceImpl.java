package com.api.project.board.serviceImpl;

import com.api.project.board.dto.Board;
import com.api.project.board.dto.BoardList;
import com.api.project.board.dto.BoardSelect;
import com.api.project.board.mapper.BoardMapper;
import com.api.project.board.service.BoardService;
import com.api.project.result.ResultEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
//@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    private int pageSize = 5;

    /**
     * BOARD 테이블의 목록을 리스트로 보여줌 ( LIMIT x,5 )
     * @param pageNoParam
     * @return
     */
    @Override
    public ResponseEntity getList(@RequestParam String pageNoParam) {
        int pageNo = Integer.parseInt(pageNoParam);
        /**
         * 전체 게시글 개수 반환
         */
        int allCount = boardMapper.getAllCount();

        if (allCount > 0) {
            /**
             * 전체 게시글
             */
            Map<String, Integer> paramMap = new HashMap<>();
            // limit 0,10
            if (pageNo == 1) {
                paramMap.put("pageNo", 0);                // 0  // offset
                paramMap.put("pageSize", this.pageSize);    // 10 // limit
                // limit
            } else {
                paramMap.put("pageNo", this.pageSize * pageNo - this.pageSize);
                paramMap.put("pageSize", this.pageSize);
            }
            List<Board> boards = boardMapper.getBoardList(paramMap);
            BoardList boardList = new BoardList();
            boardList.setResultCode(ResultEnum.OK.getResultCode());
            boardList.setResultMsg(ResultEnum.OK.getResultMsg());
            boardList.setBoardCnt(allCount);
            if (boards.size() > 0) {
                boardList.setBoardList(boards);
            }


            return new ResponseEntity(boardList, HttpStatus.OK);
        } else {
            return null;
        }
    }

    /**
     * BOARD 테이블의 레코드 하나를 가져옴
     * @param boardNo
     * @return
     */
    @Override
    public ResponseEntity getBoard(String boardNo) {
        Board board = boardMapper.getBoard(boardNo);
        BoardSelect boardSelect = new BoardSelect();
        boardSelect.setResultCode(ResultEnum.OK.getResultCode());
        boardSelect.setResultMsg(ResultEnum.OK.getResultMsg());
        if (board != null) {
            boardSelect.setBoard(board);
        }
        return new ResponseEntity(boardSelect,HttpStatus.OK);
    }

    /**
     * BOARD 테이블에 INSERT
     * @param paramMap
     * @return
     */
    @Override
    public ResponseEntity insertBoard(Map<String, String> paramMap) {
        boolean isValidPass = false;
        if (
                paramMap.get("studentSeqId") != null
                && !paramMap.get("studentSeqId").trim().equals("")
                && paramMap.get("boardTitle") != null
                && !paramMap.get("boardTitle").trim().equals("")
                && paramMap.get("boardBody") != null
                && !paramMap.get("boardBody").trim().equals("")
        ) {
            isValidPass = true;
        }else{
            return new ResponseEntity(ResultEnum.ARGUMENTS_NOT_ENOUGH, HttpStatus.BAD_REQUEST);
        }
        if (isValidPass) {
            int result = boardMapper.insertBoard(paramMap);
            if (result > 0) {
                return new ResponseEntity(ResultEnum.OK, HttpStatus.OK);
            }
        }
        return null;
    }
}
