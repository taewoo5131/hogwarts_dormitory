package com.api.project.file.mapper;

import com.api.project.file.dto.FileUploadDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {
    int fileUpload(FileUploadDto fileUploadDto);
}
