package com.api.project.login.mapper;

import com.api.project.login.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

@Mapper
public interface LoginMapper {
    int login(HashMap<String, Object> paramMap);
}
