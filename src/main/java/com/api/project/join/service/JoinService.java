package com.api.project.join.service;

import com.api.project.join.dto.StudentDto;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public interface JoinService {
    ResponseEntity insertStudent(StudentDto studentDto);
}
