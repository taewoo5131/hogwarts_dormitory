package com.api.project.board.serviceImpl;

import com.api.project.board.mapper.BoardMapper;
import org.junit.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
@MybatisTest
public class BoardServiceImplTest {

    @Autowired
    BoardMapper boardMapper;

    @Test
    public void 총개수테스트() {
        System.out.println(boardMapper);
    }

}