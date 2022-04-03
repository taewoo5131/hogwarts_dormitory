package com.api.project.file.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadResponseDto {
    private String resultCode;
    private String resultMsg;
    private String fileSeqId;
    private String fileName;
    private String filePath;
    private String fileUploadDt;
}
