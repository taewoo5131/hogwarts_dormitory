package com.api.project.file.dto;

import lombok.Data;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@Data
public class FileUploadDto {

    private String boardSeqId;
    private String fileUrl;
    private String fileNm;
    private String fileSize;
    private LocalDateTime fileUploadDt;

}
