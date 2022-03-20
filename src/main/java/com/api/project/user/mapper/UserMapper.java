package com.api.project.user.mapper;

import com.api.project.user.dto.LoginStudentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.Map;

@Mapper
public interface UserMapper {
    LoginStudentDto login(Map<String, String> paramMap);

    String getSalt(String studentId);

    int changeRefreshToken(HashMap<String, String> paramMap);

    int logout(Map<String, String> paramMap);
}
