package com.api.project.login.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public interface LoginService {
    Integer login(HashMap<String,Object> paramMap);
}
