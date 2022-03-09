package com.api.project.login.dto;

import lombok.Data;

@Data
public class LoginStudentDto {
    private String resultCode;
    private String resultMsg;
    private String studentId;
    private String studentName;
    private String dormitoryId;
    private String token;
}
