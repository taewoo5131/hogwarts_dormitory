package com.api.project.join.dto;

import lombok.Data;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;

@Data
public class StudentDto {
    @NotNull(message = "이름은 필수 입력 값입니다.")
    @NotEmpty(message = "이름은 필수 입력 값입니다.")
    @NotBlank(message = "공백은 넣을 수 없습니다.")
    private String studentName;

    @NotNull(message = "ID는 필수 입력 값입니다.")
    @NotEmpty(message = "ID는 필수 입력 값입니다.")
    @NotBlank(message = "공백은 넣을 수 없습니다.")
    @Size(min = 5,message = "ID는 5자 이상이여야 합니다.")
    private String studentId;

    @NotNull(message = "PW는 필수 입력 값입니다.")
    @NotEmpty(message = "PW는 필수 입력 값입니다.")
    @NotBlank(message = "공백은 넣을 수 없습니다.")
//    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
//            message = "비밀번호는 영문 대,소문자와 숫자,특수기호가 적어도 1개이상씩 포함된 8 ~ 20자의 비밀번호여야 합니다."
//    )
    private String studentPw;

    @NotNull(message = "PW는 필수 입력 값입니다.")
    @NotEmpty(message = "PW는 필수 입력 값입니다.")
    @NotBlank(message = "공백은 넣을 수 없습니다.")
//    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
//            message = "비밀번호는 영문 대,소문자와 숫자,특수기호가 적어도 1개이상씩 포함된 8 ~ 20자의 비밀번호여야 합니다."
//    )
    private String studentPwCh;

    @NotNull(message = "기숙사는 필수 입력 값입니다.")
    @NotEmpty(message = "기숙사는 필수 입력 값입니다.")
    @NotBlank(message = "공백은 넣을 수 없습니다.")
    private String dormitoryId;
}
