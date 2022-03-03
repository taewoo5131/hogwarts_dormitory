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
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String dormitoryName = (String)session.getAttribute("dormitoryName");
        log.info("인터셉터에서 session check => {} ", dormitoryName);
        return false;
//        if (dormitoryName.equals("") || dormitoryName.equals("0")) {
//            return false;
//        }else{
//            return true;
//        }
    }
}
