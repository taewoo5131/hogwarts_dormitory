package com.api.project.interceptor;

import com.api.project.config.JwtTokenProvider;
import com.api.project.exception.TokenException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
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
@Component
public class TokenCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String[] requestTokenArr = request.getHeader("Authorization").split("Bearer");
        String requestToken = requestTokenArr[1];
        if (requestToken == null || requestToken.equals("")) {
            return false;
        }
        log.info("TokenCheckInterceptor 호출 token 정보 >> {}", requestToken);
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        String userPk = jwtTokenProvider.getUserPk(requestToken);
        try {
            System.out.println(userPk);
        } catch (Exception k) {
            System.out.println("시발");
        }
        try {
            boolean b = jwtTokenProvider.validAccessToken(requestToken);
            if (b) {
                log.info("accessToken 유효");
            } else {
                log.info("accessToken 사용불가");
            }
            // Access Token 만료
        } catch (ExpiredJwtException e) {
//            throw new TokenException("access token이 유효하지 않습니다.");
        } catch (Exception e) {
            throw new Exception();
        }


//        System.out.println(b);

        return true;
    }
}
