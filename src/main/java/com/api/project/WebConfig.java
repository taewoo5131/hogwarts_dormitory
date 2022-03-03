package com.api.project;

import com.api.project.interceptor.DormitoryCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new DormitoryCheckInterceptor())
//                .order(1)
                .addPathPatterns("/**");
//                .excludePathPatterns("/", ".members/add", "/login", "/logout", "/css/**", "/*.ico", "/error");
    }
}
