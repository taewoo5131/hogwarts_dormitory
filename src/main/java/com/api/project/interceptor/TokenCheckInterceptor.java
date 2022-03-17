package com.api.project.interceptor;

import com.api.project.token.JwtTokenProvider;
import com.api.project.token.mapper.JwtTokenMapper;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Access Token 검증
 * /dormitory ,/join , /user/** ( /login , /logout ) 을 제외한 모든 요청에 interceptor
 */
@Slf4j
public class TokenCheckInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtTokenMapper jwtTokenMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String[] requestTokenArr = request.getHeader("Authorization").split("Bearer");
        String requestToken = requestTokenArr[1];
        /**
         * Access Token이 없는 경우
         */
        if (requestToken == null || requestToken.equals("")) {
            // throw Exception
            return false;
        }
        log.info("TokenCheckInterceptor 호출 token 정보 >> {}", requestToken);
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        /**
         * Access Token 만료시간 검증
         */
        try {
            boolean b = jwtTokenProvider.validAccessToken(requestToken);
            if (b) {
                return true;
            }
        /**
         * Access Token이 만료된 경우
         */
        } catch (ExpiredJwtException/* | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException*/ e) {
            e.printStackTrace();
            log.error("유효하지 않은 토큰. {} ", e.getClaims().get("pk"));
            System.out.println(jwtTokenMapper);
            /**
             * Refresh Token 검증
             */

            return false;
//            try {
//                String userPk = jwtTokenProvider.getUserPk(requestToken);
//                System.out.println(userPk);
//            } catch (Exception k) {
//                System.out.println("시발");
//            }
        }
        return true;
    }
}
