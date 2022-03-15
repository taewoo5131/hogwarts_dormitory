package com.api.project.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Access Token 검증
 * /dormitory , /user/** ( /login , /logout ) 을 제외한 모든 요청에 interceptor
 */
@Slf4j
@Component
public class TokenCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getHeader("Authorization") == null || request.getHeader("Authorization").equals("")) {
            return false;
        }
        log.info("TokenCheckInterceptor 호출");
        return true;
    }
}
