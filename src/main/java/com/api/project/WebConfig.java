package com.api.project;

import com.api.project.interceptor.DormitoryCheckinterceptor;
import com.api.project.interceptor.TokenCheckInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 스프링 인터셉터 등록
 */
@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public TokenCheckInterceptor tokenCheckInterceptor() {
        return new TokenCheckInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DormitoryCheckinterceptor()).order(1).addPathPatterns("/**").excludePathPatterns("/dormitory","/swagger-ui");

        // Bean에 등록된 TokenCheckInterceptor를 등록
        registry.addInterceptor(tokenCheckInterceptor()).order(2).addPathPatterns("/**").excludePathPatterns("/dormitory", "/join" ,"/user/login","/swagger-ui");
    }

}
