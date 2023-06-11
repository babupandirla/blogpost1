package com.wrapy.blogpost.configuration;

import com.wrapy.blogpost.interceptor.CustomInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class ServiceInterceptorConfig implements WebMvcConfigurer {
    @Bean
    CustomInterceptor getCustomInterceptor(){
        return new CustomInterceptor();

    }
}
