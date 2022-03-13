package com.api.project.config;

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

    @Value("${jwt.secret}")
    private String secretKey;



    // access 토큰 유효시간 30분
    private long aceessTokenValidTime = 30 * 60 * 1000L;

    // resfresh 토큰 유효기간 2주
    LocalDate today = LocalDate.now();
    LocalDate next2Week = today.plus(2, ChronoUnit.WEEKS);
    Instant instant = next2Week.atStartOfDay(ZoneId.systemDefault()).toInstant();
    Date twoWeeksAfter = Date.from(instant);

    // 객체 초기화, secretKey를 Base64로 인코딩
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // JWT Access 토큰 생성
    public String makeAccessJwtToken(String studentId) {
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("fresh")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + aceessTokenValidTime)) // 30분
                .claim("id", studentId)
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

    // 토큰에서 회원 정보 추출
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // Request의 Header에서 token 값을 가져옴. "X-AUTH-TOKEN" : "TOKEN값'
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}