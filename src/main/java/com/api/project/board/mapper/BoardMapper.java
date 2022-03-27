package com.api.project.board.mapper;

import com.api.project.board.dto.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
    int getAllCount();

    List<Board> getBoardList(Map<String, Integer> paramMap);

    Board getBoard(String boardNo);

    List getFileList(String boardNo);

    int insertBoard(Map<String, String> paramMap);

    int updateBoard(Map<String, String> paramMap);

    int deleteBoard(Map<String, Integer> paramMap);
}
