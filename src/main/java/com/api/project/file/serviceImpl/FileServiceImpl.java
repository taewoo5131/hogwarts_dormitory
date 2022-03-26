package com.api.project.file.serviceImpl;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.internal.Mimetypes;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import com.api.project.exception.FileUploadFailException;
import com.api.project.file.dto.FileUploadDto;
import com.api.project.file.mapper.FileMapper;
import com.api.project.file.service.FileService;
import com.api.project.result.ResultEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MimeType;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class FileServiceImpl implements FileService {

    @Value("${cloud.aws.s3.bucket}")
    String bucket;

    private final AmazonS3Client amazonS3Client;
    private final FileMapper fileMapper;

    @Override
    public ResponseEntity uploadFile(MultipartFile file, String boardSeqId) throws FileUploadFailException{
        if (file.isEmpty()) {
            throw new IllegalArgumentException("file is empty");
        }

        String fileName = file.getOriginalFilename();
        Long fileSize = file.getSize();
        /**
         * fileSize 40MB 까지
         */
        if (fileSize > 40 * 1024 * 1024) {
            throw new FileUploadFailException("fileSize over this fileSize is " + fileSize / 1024 / 1024);
        }

        /**
         * file Upload & DB insert
         */
        try {
            /**
             * file Upload
             */
            // file의 inputStream을 byte Array로 변환
            InputStream inputStream = file.getInputStream();
            byte[] bytes = IOUtils.toByteArray(inputStream);

            // byte로 변환된 inputStream을 byteArrayInputStream으로 변환
            ByteArrayInputStream byteArrayIs = new ByteArrayInputStream(bytes);

            // s3에 file을 byte로 올리기때문에 해당 파일에 대한 정보가 없다. 그래서 ObjectMetadata에 파일의 정보를 추가해서 넘겨준다.
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(Mimetypes.getInstance().getMimetype(fileName));
            objectMetadata.setContentLength(bytes.length);

            // s3에 업로드
            amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, byteArrayIs, objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
            String fileUrl = amazonS3Client.getUrl(bucket, fileName).toString();

            /**
             * DB insert
             */
            FileUploadDto fileUploadDto = new FileUploadDto();
            fileUploadDto.setFileUploadDt(LocalDateTime.now());
            fileUploadDto.setBoardSeqId(boardSeqId);
            fileUploadDto.setFileNm(fileName);
            fileUploadDto.setFileUrl(fileUrl);
            fileUploadDto.setFileSize(String.valueOf(fileSize));
            int result = fileMapper.fileUpload(fileUploadDto);
            if (result > 0) {
                log.error("[FileServiceImpl] [uploadFile] > {} ", "파일업로드 성공 : " + fileUploadDto.toString());
                return new ResponseEntity(ResultEnum.OK, HttpStatus.OK);
            }
        } catch (IOException e) {
            throw new FileUploadFailException("file upload failed");
        }

        return null;
    }
}
