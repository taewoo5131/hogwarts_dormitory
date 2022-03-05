package com.api.project.join.mapper;

import com.api.project.join.dto.StudentDto;

import java.util.HashMap;

public interface JoinMapper {
    Integer insertStudent(StudentDto studentDto);
}
