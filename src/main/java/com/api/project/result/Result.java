package com.api.project.result;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum Result {
    OK(HttpStatus.OK, "0000","정상"),
    MUGGLE(HttpStatus.BAD_REQUEST,"0001","머글이므로 접근이 불가합니다."),
    NO_DORMITORY(HttpStatus.BAD_REQUEST,"0002","기숙사 배정을 받지 않았습니다."),
    NO_TOKEN(HttpStatus.UNAUTHORIZED, "0003", "토큰값이 유효하지 않습니다."),
    ARGUMENTS_NOT_ENOUGH(HttpStatus.BAD_REQUEST,"0004","필수값 누락"),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"0010","서버를 이용할 수 없습니다.");

    private HttpStatus status;
    private String resultCode;
    private String resultMsg;


    Result(HttpStatus status, String resultCode, String resultMsg) {
        this.status = status;
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }
}
