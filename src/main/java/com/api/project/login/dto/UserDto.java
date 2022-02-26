package com.api.project.login.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserDto {
    private String userId;
    private String userName;
    private int userPw;
}
