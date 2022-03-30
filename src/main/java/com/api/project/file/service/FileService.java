package com.api.project.file.service;

import com.api.project.exception.FileUploadFailException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    ResponseEntity uploadFile(MultipartFile file, String boardSeqId) throws FileUploadFailException;

    ResponseEntity downloadFile(String fileName) throws IOException;
}
