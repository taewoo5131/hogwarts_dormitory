package com.api.project.user.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface LoginService {
    ResponseEntity login(Map<String , String> paramMap);
}
