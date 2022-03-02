package com.api.project.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class interceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DormitoryCheckInterceptor())
                .order(1)
                .addPathPatterns("/**");
//                .excludePathPatterns("/", ".members/add", "/login", "/logout", "/css/**", "/*.ico", "/error");
    }
}
