package com.api.project.join.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class StudentDto {
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String studentName;

    @NotBlank(message = "ID는 필수 입력 값입니다.")
    private String studentId;

    @NotBlank(message = "PW는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자,특수기호가 적어도 1개이상씩 포함된 8 ~ 20자의 비밀번호여야 합니다."
    )
    private String studentPw;

    @NotBlank(message = "PW는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자,특수기호가 적어도 1개이상씩 포함된 8 ~ 20자의 비밀번호여야 합니다."
    )
    private String studentPwCh;

    @NotBlank(message = "기숙사는 필수 입력 값입니다.")
    private String dormitoryId;
}
