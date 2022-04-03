package com.api.project.file.dto;

import lombok.Data;

@Data
public class FileEntity {
    private String fileSeqId;
    private String fileUploadDt;
    private String boardSeqId;
    private String fileUrl;
    private String fileNm;
    private String fileSize;
}
