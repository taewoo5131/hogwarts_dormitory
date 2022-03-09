package com.api.project.login.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface LoginService {
    ResponseEntity login(Map<String , String> paramMap);
}
