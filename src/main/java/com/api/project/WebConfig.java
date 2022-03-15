package com.api.project;

import com.api.project.interceptor.DormitoryCheckinterceptor;
import com.api.project.interceptor.TokenCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DormitoryCheckinterceptor()).order(1).addPathPatterns("/**").excludePathPatterns("/dormitory","/swagger-ui");
        registry.addInterceptor(new TokenCheckInterceptor()).order(2).addPathPatterns("/**").excludePathPatterns("/dormitory", "/user/**","/swagger-ui");
    }

}
