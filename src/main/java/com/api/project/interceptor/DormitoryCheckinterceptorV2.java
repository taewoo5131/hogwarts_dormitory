package com.api.project.interceptor;

import com.api.project.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Component
public class DormitoryCheckinterceptorV2 implements HandlerInterceptor {

    /**
     * desc session에 dormitoryId이 없으면 기숙사 배정을 받지 않았으므로 login 및 회원가입 등등 접근 불가
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String dormitoryId = (String) session.getAttribute("dormitoryId");
        log.info("인터셉터에서 session check => {} ", dormitoryId);
        try {
            if (dormitoryId.equals("") || dormitoryId.equals("0")) {
//                response.sendRedirect(request.getContextPath() + "/dormitory");
                log.info("인터셉터에서 session check dormitoryId이 올바르지 않음. => {} ", dormitoryId);

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{\"resultCode\":\"0002\",\"resultMsg\":\"기숙사 배정을 받지 않았습니다.\"}");
                return false;
            } else if (dormitoryId.equals("5")) {
                log.info("머글이 접근함 ! ");
                System.out.println(Result.MUGGLE.toString());
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(Result.MUGGLE.toString());
//                response.getWriter().write("{\"resultCode\":\"0001\",\"resultMsg\":\"머글이므로 접근이 불가합니다.\"}");
                return false;
            } else {
                return true;
            }
        } catch (NullPointerException e) {
//            response.sendRedirect(request.getContextPath() + "/dormitory");
            log.error("인터셉터에서 dormitoryId Null");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"resultCode\":\"0002\",\"resultMsg\":\"기숙사 배정을 받지 않았습니다.\"}");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
//        return true;
    }
}
