package com.api.project.login.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public interface LoginService {

    Integer postLogin(HashMap<String,Object> paramMap) throws NoSuchAlgorithmException;
}
