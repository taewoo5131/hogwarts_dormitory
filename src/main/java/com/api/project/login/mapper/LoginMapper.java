package com.api.project.login.mapper;

import com.api.project.login.dto.LoginStudentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface LoginMapper {
    LoginStudentDto login(Map<String, String> paramMap);

    String getSalt(String studentId);
}
