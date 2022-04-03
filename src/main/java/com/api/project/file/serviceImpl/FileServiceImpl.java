package com.api.project.file.serviceImpl;
import com.api.project.exception.FileUploadFailException;
import com.api.project.file.dto.FileEntity;
import com.api.project.file.dto.FileUploadDto;
import com.api.project.file.dto.FileUploadResponseDto;
import com.api.project.file.mapper.FileMapper;
import com.api.project.file.service.FileService;
import com.api.project.result.ResultEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.Null;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MimeType;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class FileServiceImpl implements FileService {

    private String filePath = "/Users/leetaewoo/Desktop/Toy-Project/uploadDir/";
    private final FileMapper fileMapper;

    @Override
    public ResponseEntity uploadFile(MultipartFile file, String boardSeqId) throws FileUploadFailException{
        try {
            // null체크
            if (file.isEmpty()) {
                throw new IllegalArgumentException("file is empty");
            }
            // DB에 올라갈 realFilename
            String originalFilename = file.getOriginalFilename();
            // dir에 저장할 storeFilename
            String storeFilename = String.valueOf(UUID.randomUUID())+"_"+originalFilename;

            // fileFullPath
            String fileFullPath = filePath + storeFilename;
            // dir에 저장 ( storeFilename이 올라간다. )
            file.transferTo(new File(fileFullPath));
            // DB에 저장 ( originalFilename이 올라간다. )
            FileUploadDto fileUploadDto = new FileUploadDto();
            fileUploadDto.setFileUploadDt(LocalDateTime.now());
            fileUploadDto.setFileNm(originalFilename);
            fileUploadDto.setFileSize(String.valueOf(file.getSize()));
            fileUploadDto.setFileUrl(fileFullPath);
            fileUploadDto.setBoardSeqId(boardSeqId);
            int i = fileMapper.fileUpload(fileUploadDto);
            // 정상 업로드 성공
            if (i > 0) {
                // fileSeqId DB에서 GET
                String fileSeqId = fileMapper.getFileSeqId(fileFullPath);
                // response DTO 생성
                FileUploadResponseDto fileUploadResponseDto = new FileUploadResponseDto(
                        ResultEnum.OK.getResultCode(), ResultEnum.OK.getResultMsg(),fileSeqId,fileFullPath,fileUploadDto.getFileNm(),String.valueOf(fileUploadDto.getFileUploadDt())
                );
                return new ResponseEntity(fileUploadResponseDto, HttpStatus.OK);
            }else{
                throw new FileUploadFailException("uploadFail");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity downloadFile(String fileSeqId) throws IOException{
        // null || valid 체크
        if (fileSeqId.isEmpty() || Integer.parseInt(fileSeqId) == 0) {
            throw new IllegalArgumentException("file Seq Id가 비정상");
        }
        // 해당 파일정보 get
        FileEntity fileInfo = fileMapper.getFileInfo(fileSeqId);
        if (fileInfo == null) {
            throw new IllegalArgumentException("fileSeqId에 해당하는 file 정보 없음");
        } else {
            Path filePath = Paths.get(fileInfo.getFileUrl());
            // 파일 resource ( 파일 내용 )
            Resource resource = new InputStreamResource(Files.newInputStream(filePath));
            // 응답 header
            HttpHeaders headers = new HttpHeaders();
            // filename header에 추가
            headers.setContentDisposition(ContentDisposition.builder("attachment").filename(fileInfo.getFileNm()).build());
            return new ResponseEntity(resource, headers, HttpStatus.OK);
        }
    }
}
