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
    NO_TOKEN( "0003", "토큰값이 유효하지 않습니다."),
    ARGUMENTS_NOT_ENOUGH("0004","필수값 누락"),
    PASSWORD_ERROR("0005", "PW를 확인하세요."),
    ALREADY_USER("0006","이미 존재하는 회원입니다."),
    LOGIN_ERROR("0007", "로그인 실패"),
    SERVER_ERROR("0010", "서버를 이용할 수 없습니다."),
    DB_ERROR("0011","DB를 이용할 수 없습니다.");

    private String resultCode;
    private String resultMsg;


    ResultEnum(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }
}
