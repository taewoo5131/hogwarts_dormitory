package com.api.project.exceptionHandler;

import com.api.project.exception.TokenException;
import com.api.project.result.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Dormitory ExceptionHandler
 */
@Slf4j
@RestControllerAdvice
public class TokenCheckInterceptorExceptionHandler {
    /**
     * token체크 인터셉터에서 throw한 예외 처리 handler
     * Access token , Refresh Token 체크
     *
     * @return
     */
    @ExceptionHandler(TokenException.class)
    public ResponseEntity tokenExceptionHandler(TokenException e) {
        log.error("[TokenCheckInterceptorExceptionHandler] [tokenExceptionHandler] > {} ", e.getMessage());
        if (e.getMessage().equals("NO_TOKEN")) {
            return new ResponseEntity(ResultEnum.NO_TOKEN, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(ResultEnum.BAD_REFRESH_TOKEN, HttpStatus.UNAUTHORIZED);
    }

    /*@ExceptionHandler(Exception.class)
    public ResponseEntity exceptionHandler(Exception e) {
        log.error("this is ExceptionController.illegalArgumentExceptionHandler {} " , e.getMessage());
        return new ResponseEntity(ResultEnum.SERVER_ERROR,HttpStatus.INTERNAL_SERVER_ERROR);
    }*/
}
