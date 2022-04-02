package com.api.project.exceptionHandler;

import com.api.project.file.controller.FileController;
import com.api.project.result.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartFile;


/**
 * FileUpload ExceptionHandler
 */
@Slf4j
@RestControllerAdvice
public class FileSizeOverExceptionHandler {
    @ExceptionHandler(SizeLimitExceededException.class)
    public ResponseEntity fileSizeOver() {
        log.error("[FileSizeOverExceptionHandler] [fileSizeOver] > {} ", "fileSize가 springBoot tomcat의 max 설정값인 20MB를 초과했습니다.");
        return new ResponseEntity(ResultEnum.SERVER_ERROR, HttpStatus.PAYLOAD_TOO_LARGE);
    }
}
