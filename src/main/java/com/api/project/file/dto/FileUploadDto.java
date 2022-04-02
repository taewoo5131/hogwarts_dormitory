package com.api.project.file.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadDto {

    private String boardSeqId;
    private String fileUrl;
    private String fileNm;
    private String fileSize;
    private LocalDateTime fileUploadDt;

}
