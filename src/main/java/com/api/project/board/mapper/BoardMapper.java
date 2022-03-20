package com.api.project.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
    int getAllCount();

//    ArrayList getBoard(Map<String, Integer> paramMap);
    ArrayList getBoard();
}
