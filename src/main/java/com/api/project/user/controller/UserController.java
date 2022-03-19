package com.api.project.user.controller;

import com.api.project.result.ResultEnum;
import com.api.project.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    /**
     * login
     *
     * @param paramMap
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Map<String, String> paramMap, HttpServletResponse response) {
        ResponseEntity result = userService.login(paramMap, response);
        return result;
    }

    /**
     * logout
     *
     * @param paramMap
     * @return
     */
    @PostMapping("/logout")
    public ResponseEntity logout(@RequestBody Map<String, String> param, HttpServletRequest request) {
        try {
            /**
             * cookie에서 token get
             */
            Cookie[] cookies = request.getCookies();
            String requestToken = "";
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    requestToken = cookie.getValue();
                }
            }
            String studentId = param.get("studentId");
            String dormitoryId = (String) request.getSession().getAttribute("dormitoryId");
//            requestToken = request.getHeader("Authorization");

            Map<String, String> paramMap = new HashMap();
            paramMap.put("studentId", studentId);
            paramMap.put("dormitoryId", dormitoryId);
            paramMap.put("token", requestToken);
            userService.logout(paramMap);
        } catch (NullPointerException e) {
            return new ResponseEntity(ResultEnum.ARGUMENTS_NOT_ENOUGH, HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}
