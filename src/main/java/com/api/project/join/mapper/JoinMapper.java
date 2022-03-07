package com.api.project.join.mapper;

import com.api.project.join.dto.StudentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
@Mapper
public interface JoinMapper {
    Integer insertStudent(StudentDto studentDto);

    Integer checkById(String id);
}
