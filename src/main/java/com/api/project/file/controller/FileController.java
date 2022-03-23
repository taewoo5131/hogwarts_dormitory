package com.api.project.file.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {

    @Value("${cloud.aws.s3.bucket}")
    String bucket;

    @PostMapping
    public void uploadFile() {
        System.out.println(bucket);
    }

    @GetMapping
    public void downloadFile() {

    }
}
