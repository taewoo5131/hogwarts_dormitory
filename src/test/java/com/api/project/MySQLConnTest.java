package com.api.project;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.DriverManager;


public class MySQLConnTest {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void 커넥션테스트() throws ClassNotFoundException {
        try {
            Connection connection = sqlSessionFactory.openSession().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
