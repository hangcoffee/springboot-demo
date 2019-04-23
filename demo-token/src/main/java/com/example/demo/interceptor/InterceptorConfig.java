package com.example.demo.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Auther: Coffee
 * @Date: 2019/4/6
 * 配置拦截器
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter{

    @Bean
    public ExecptionInterceptor execptionInterceptor() {
        return new ExecptionInterceptor();
    }

    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(execptionInterceptor()).addPathPatterns("/api/**");
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/api/v/**");
        super.addInterceptors(registry);
    }
}
