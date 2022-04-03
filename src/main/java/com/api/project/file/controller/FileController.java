package com.api.project.file.controller;

import com.api.project.exception.FileUploadFailException;
import com.api.project.file.service.FileService;
import com.api.project.result.ResultEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController{

    private final FileService fileService;

    @PostMapping
    public ResponseEntity uploadFile(@RequestParam(value = "file") MultipartFile file, @RequestParam String boardSeqId){
        try {
            ResponseEntity response = fileService.uploadFile(file, boardSeqId);
            return response;
        } catch (FileUploadFailException e) {
            log.error("[FileController] [uploadFile] > {}", e.getMessage());
            return new ResponseEntity(ResultEnum.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IllegalArgumentException e) {
            log.error("[FileController] [uploadFile] > {}", e.getMessage());
            return new ResponseEntity(ResultEnum.ARGUMENTS_NOT_ENOUGH, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{fileSeqId}")
    public ResponseEntity downloadFile(@PathVariable String fileSeqId) {
        try {
            ResponseEntity response = fileService.downloadFile(fileSeqId);
            return response;
        } catch (IllegalArgumentException e) {
            log.error("[FileController] [downloadFile] > {}", e.getMessage());
            return new ResponseEntity(ResultEnum.ARGUMENTS_NOT_ENOUGH, HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            log.error("[FileController] [downloadFile] > {}", "IOException 발생");
            return new ResponseEntity(ResultEnum.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
