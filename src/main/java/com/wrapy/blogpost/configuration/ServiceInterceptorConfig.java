package com.wrapy.blogpost.configuration;

import com.wrapy.blogpost.interceptor.CustomInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ServiceInterceptorConfig implements WebMvcConfigurer {
    @Bean
    CustomInterceptor getCustomInterceptor(){
        return new CustomInterceptor();

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getCustomInterceptor());
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
