package com.api.project.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.ToString;


/**
 * desc ErrorCode , ErrorMsg 모음
 */
@Getter
@ToString
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ResultEnum {
    OK("0000","정상"),
    MUGGLE("0001","머글이므로 접근이 불가합니다."),
    NO_DORMITORY("0002","기숙사 배정을 받지 않았습니다."),
    ARGUMENTS_NOT_ENOUGH("0005","필수값 누락"),
    PASSWORD_ERROR("0006", "PW를 확인하세요."),
    ALREADY_USER("0007","이미 존재하는 회원입니다."),
    LOGIN_ERROR("0008", "로그인 실패"),
    SERVER_ERROR("0010", "서버를 이용할 수 없습니다."),
    DB_ERROR("0011","DB를 이용할 수 없습니다."),

    NO_TOKEN("0030", "토큰값이 없습니다."),
    BAD_ACCESS_TOKEN("0031", "Access 토큰값이 유효하지 않아 새로운 토큰 발급"),
    BAD_REFRESH_TOKEN("0032", "Refresh 토큰값이 유효하지 않습니다.");
    private String resultCode;
    private String resultMsg;


    ResultEnum(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }
}
