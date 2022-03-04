package com.api.project.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Component
public class DormitoryCheckinterceptorV2 implements HandlerInterceptor {

    /**
     * desc session에 dormitoryName이 없으면 기숙사 배정을 받지 않았으므로 login 및 회원가입 등등 접근 불가
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String dormitoryName = (String) session.getAttribute("dormitoryName");
        log.info("인터셉터에서 session check => {} ", dormitoryName);
        try {
            if (dormitoryName.equals("") || dormitoryName.equals("0")) {
                response.sendRedirect(request.getContextPath() + "/dormitory");
                log.info("인터셉터에서 session check dormitoryName이 올바르지 않음. => {} ", dormitoryName);
                return false;
            } else {
                return true;
            }
        } catch (NullPointerException e) {
            response.sendRedirect(request.getContextPath() + "/dormitory");
            log.error("인터셉터에서 dormitoryName Null");
            return false;
        }
//        return true;
    }
}
