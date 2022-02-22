package com.api.project.login.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    private String userId;
    private String userName;
    private int userPw;
}
