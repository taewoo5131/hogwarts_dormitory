package com.api.project.board.mapper;

import com.api.project.board.dto.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
    int getAllCount();

    List<Board> getBoard(Map<String, Integer> paramMap);
//    ArrayList getBoard();
}
