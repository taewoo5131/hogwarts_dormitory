package com.api.project.join.service;

import com.api.project.join.dto.StudentDto;

import java.util.HashMap;

public interface JoinService {
    Integer insertStudent(StudentDto studentDto);
}