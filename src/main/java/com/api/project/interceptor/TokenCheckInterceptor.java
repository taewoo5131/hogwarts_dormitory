package com.api.project.interceptor;

import com.api.project.result.ResultEnum;
import com.api.project.token.JwtTokenProvider;
import com.api.project.token.exception.TokenException;
import com.api.project.token.mapper.JwtTokenMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.Null;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Access Token 및 Refresh Token 검증
 * /dormitory ,/join , /user/login  을 제외한 모든 요청에 interceptor
 */
@Slf4j
public class TokenCheckInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtTokenMapper jwtTokenMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestToken = "";
        try {
            /**
             * Http Header에서 token get
             */
//            requestToken = request.getHeader("Authorization");

            /**
             * cookie에서 token get
             */
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    requestToken = cookie.getValue();
                }
            }

            log.info("TokenCheckInterceptor 호출 token 정보 >> {}", requestToken);
            JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
            /**
             * Access Token 만료시간 검증
             */
            try {
                boolean b = jwtTokenProvider.validAccessToken(requestToken);
                if (b) {
                    log.info("AccessToken 유효");
                    return true;
                }
                /**
                 * Access Token이 만료된 경우
                 */
            } catch (ExpiredJwtException/* | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException*/ e) {
                e.printStackTrace();
                log.error("유효하지 않은 토큰. {} ", e.getClaims().get("pk"));
                /**
                 * Refresh Token 검증
                 */
                String requestPk = String.valueOf(e.getClaims().get("pk"));
                String refreshToken = jwtTokenMapper.getRefreshToken(requestPk);
                try {
                    boolean b = jwtTokenProvider.validAccessToken(refreshToken);
                    if (b) {
                        /**
                         * Access Token 재발급
                         */
                        log.info("AccessToken 재발급");
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("resultCode", ResultEnum.BAD_ACCESS_TOKEN.getResultCode());
                        jsonObject.put("resultMsg", ResultEnum.BAD_ACCESS_TOKEN.getResultMsg());
                        jsonObject.put("token", new JwtTokenProvider().makeAccessJwtToken(requestPk));
                        response.setCharacterEncoding("utf-8");
                        response.setContentType("application/json");
                        response.getWriter().write(String.valueOf(jsonObject));

                        /**
                         * Access Token 쿠키에 저장
                         */
                        Cookie cookie = new Cookie("token",new JwtTokenProvider().makeAccessJwtToken(requestPk));
                        cookie.setHttpOnly(true);
                        cookie.setPath("/");
                        response.addCookie(cookie);
                    }
                /**
                 * Refresh Token이 만료된 경우
                 */
                } catch (ExpiredJwtException q) {
                    /**
                     * return 토큰이 만료되었습니다.
                     */
                    log.error("RefreshToken도 만료");

                    /**
                     * 로그아웃
                     */
                    throw new TokenException("BAD_REFRESH_TOKEN");
                }
                return false;
            }
            return true;
        /**
         * HTTP header에 Authorization 자체가 없을 경우
         */
        } catch (NullPointerException | IllegalArgumentException e) {
            log.info("HTTP token 자체가 null");
            throw new TokenException("NO_TOKEN");
        }

    }
}
