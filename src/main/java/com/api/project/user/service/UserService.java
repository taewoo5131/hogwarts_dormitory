package com.api.project.user.service;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface UserService {
    ResponseEntity login(Map<String, String> paramMap, HttpServletResponse response);

    ResponseEntity logout(Map<String, String> paramMap, HttpServletRequest request, HttpServletResponse response);

}
