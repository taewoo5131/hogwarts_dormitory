package com.api.project.token;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.*;

@Component
public class JwtTokenProvider {

//    @Value("${jwt.secret}")
    private String secretKey = "hogwartapiproject2022leetaewoo";
    // access 토큰 유효시간 30분
//    private final long aceessTokenValidTime = 30 * 60 * 1000L;
    private final long aceessTokenValidTime = 1000L;

    // resfresh 토큰 유효기간 2주
    LocalDate today = LocalDate.now();
    LocalDate next2Week = today.plus(2, ChronoUnit.WEEKS);
    Instant instant = next2Week.atStartOfDay(ZoneId.systemDefault()).toInstant();
    private final Date twoWeeksAfter = Date.from(instant);

    // 객체 초기화, secretKey를 Base64로 인코딩
    @PostConstruct
    protected void init() {
        this.secretKey = Base64.getEncoder().encodeToString(this.secretKey.getBytes());
    }

    // JWT Access 토큰 생성
    public String makeAccessJwtToken(String studentSeqId) {
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("fresh")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + aceessTokenValidTime)) // 30분
                .claim("pk", studentSeqId)
                .signWith(SignatureAlgorithm.HS256, this.secretKey)
                .compact();
    }

    // JWT Refresh 토큰 생성
    public String makeRefreshJwtToken() {
        Date now = new Date();
        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(twoWeeksAfter) // 2주
                .signWith(SignatureAlgorithm.HS256, this.secretKey)
                .compact();
    }

    // 토큰에서 회원 정보 추출 ( STUDENT_SEQ_ID )
    public String getUserPk(String token) {
        return (String)Jwts.parser().setSigningKey(this.secretKey.getBytes()).parseClaimsJws(token).getBody().get("pk");
    }

    // access 토큰 검증 + 만료시간 확인
    public boolean validAccessToken(String token) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(this.secretKey.getBytes()).parseClaimsJws(token);
        return !claims.getBody().getExpiration().before(new Date());
    }
}