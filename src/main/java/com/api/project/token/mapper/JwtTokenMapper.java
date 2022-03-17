package com.api.project.token.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JwtTokenMapper {
    String getRefreshToken(String studentSeqId);
}
