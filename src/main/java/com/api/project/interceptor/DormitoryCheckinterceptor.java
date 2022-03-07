package com.api.project.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Component
public class DormitoryCheckinterceptor implements HandlerInterceptor {

    /**
     * desc session에 dormitoryId이 없으면 기숙사 배정을 받지 않았으므로 login 및 회원가입 등등 접근 불가
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String dormitoryId = (String) session.getAttribute("dormitoryId");
        System.out.println(request.getContextPath());
        try {
            if (dormitoryId.equals("") || dormitoryId.equals("0")) {
                throw new IllegalArgumentException("기숙사 배정을 받지 않았습니다.");
            } else if (dormitoryId.equals("5")) {
                throw new IllegalArgumentException("MUGGLE");
            // 정상 케이스
            } else {
                return true;
            }
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("기숙사 배정을 받지 않았습니다.");
        }
    }
}
