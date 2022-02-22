package com.api.project.login.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface LoginMapper {
    int login(HashMap<String, Object> paramMap);
}
