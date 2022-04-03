package com.api.project.board.serviceImpl;

import com.api.project.board.dto.Board;
import com.api.project.board.dto.BoardList;
import com.api.project.board.dto.BoardSelect;
import com.api.project.board.mapper.BoardMapper;
import com.api.project.board.service.BoardService;
import com.api.project.result.ResultEnum;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
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
            boardList.setBoardCnt(String.valueOf(allCount));
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
        /**
         * 게시글 + 작성자 단건 조회
         */
        Board board = boardMapper.getBoard(boardNo);
        BoardSelect boardSelect = new BoardSelect();
        boardSelect.setResultCode(ResultEnum.OK.getResultCode());
        boardSelect.setResultMsg(ResultEnum.OK.getResultMsg());
        if (board != null) {
            boardSelect.setStudentSeqId(board.getStudentSeqId());
            boardSelect.setStudentName(board.getStudentName());
            boardSelect.setStudentId(board.getStudentId());
            boardSelect.setDormitoryId(board.getDormitoryId());
            boardSelect.setBoardSeqId(board.getBoardSeqId());
            boardSelect.setBoardTitle(board.getBoardTitle());
            boardSelect.setBoardBody(board.getBoardBody());
        }
        /**
         * 해당 게시글 파일리스트 조회
         */
        List<Map<String, Object>> fileList = boardMapper.getFileList(boardNo);
        // String으로 convert 후 return해주기 위해
        for (int i = 0; i < fileList.size(); i++) {
            fileList.get(i).put("fileSeqId" , String.valueOf(fileList.get(i).get("fileSeqId")));
            fileList.get(i).put("fileUploadDt" , String.valueOf(fileList.get(i).get("fileUploadDt")));
        }

        boardSelect.setFileList(fileList);
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
            log.error("[BoardServiceImpl] [insertBoard] > {} ", "필수값 누락");
            return new ResponseEntity(ResultEnum.ARGUMENTS_NOT_ENOUGH, HttpStatus.BAD_REQUEST);
        }
        if (isValidPass) {
            int result = boardMapper.insertBoard(paramMap);
            if (result > 0) {
                log.info("[BoardServiceImpl] [insertBoard] > {} ", paramMap.toString());
                return new ResponseEntity(ResultEnum.OK, HttpStatus.CREATED);
            }
        }
        return null;
    }

    /**
     * BOARD 테이블에 UPDATE
     * @param paramMap
     * @return
     */
    @Override
    public ResponseEntity updateBoard(Map<String, String> paramMap) {
        boolean isValidPass = false;
        if (
                paramMap.get("boardSeqId") != null
                && !paramMap.get("boardSeqId").trim().equals("")
                && paramMap.get("studentSeqId") != null
                && !paramMap.get("studentSeqId").trim().equals("")
                && paramMap.get("boardTitle") != null
                && !paramMap.get("boardTitle").trim().equals("")
                && paramMap.get("boardBody") != null
                && !paramMap.get("boardBody").trim().equals("")
        ) {
            isValidPass = true;
        }else{
            log.error("[BoardServiceImpl] [updateBoard] > {} ", "필수값 누락");
            return new ResponseEntity(ResultEnum.ARGUMENTS_NOT_ENOUGH, HttpStatus.BAD_REQUEST);
        }
        if (isValidPass) {
            Board board = boardMapper.getBoard(String.valueOf(paramMap.get("boardSeqId")));
            // 내가 작성한 글만 삭제가능
            if (board.getStudentSeqId().equals(String.valueOf(paramMap.get("studentSeqId")))) {
                int result = boardMapper.updateBoard(paramMap);
                if (result > 0) {
                    log.info("[BoardServiceImpl] [updateBoard] > {} ", paramMap.toString());
                    return new ResponseEntity(ResultEnum.OK, HttpStatus.OK);
                }
            } else {
                log.info("[BoardServiceImpl] [updateBoard] > {}, ", "권한이 없는 사용자입니다.");
                return new ResponseEntity(ResultEnum.NO_PERMISSION, HttpStatus.BAD_REQUEST);
            }
        }
        return null;
    }

    /**
     * BOARD 테이블에 DELETE
     * @param paramMap
     * @return
     */
    @Override
    public ResponseEntity deleteBoard(Map<String, Integer> paramMap) {
        boolean isValidPass = false;
        if (
                paramMap.get("boardSeqId") != null
                && !String.valueOf(paramMap.get("boardSeqId")).trim().equals("")
                && paramMap.get("studentSeqId") != null
                && !String.valueOf(paramMap.get("studentSeqId")).trim().equals("")
        ) {
            isValidPass = true;
        }else{
            log.error("[BoardServiceImpl] [deleteBoard] > {} ", "필수값 누락");
            return new ResponseEntity(ResultEnum.ARGUMENTS_NOT_ENOUGH, HttpStatus.BAD_REQUEST);
        }
        if (isValidPass) {
            Board board = boardMapper.getBoard(String.valueOf(paramMap.get("boardSeqId")));
            // 내가 작성한 글만 삭제가능
            if (board.getStudentSeqId().equals(String.valueOf(paramMap.get("studentSeqId")))) {
                int result = boardMapper.deleteBoard(paramMap);
                if (result > 0) {
                    return new ResponseEntity(ResultEnum.OK, HttpStatus.OK);
                }
            } else {
                log.info("[BoardServiceImpl] [deleteBoard] > {}, ", "권한이 없는 사용자입니다.");
                return new ResponseEntity(ResultEnum.NO_PERMISSION, HttpStatus.BAD_REQUEST);
            }

        }
        return null;
    }
}
