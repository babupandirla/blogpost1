package com.wrapy.blogpost.interceptor;

import com.wrapy.blogpost.entity.ApiData;
import com.wrapy.blogpost.repositories.ApiDataRepository;
import com.wrapy.blogpost.repositories.StatusResponseRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CustomInterceptor implements HandlerInterceptor {
    @Autowired
    ApiDataRepository apiDataRepository;
    @Autowired
    StatusResponseRepository statusResponseRepository;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        request.setAttribute("startTime", System.currentTimeMillis());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println(response.getStatus());
        System.out.println(request.getRequestURL());
        ApiData a1=new ApiData();
        a1.setUrl(request.getRequestURL().toString());
        a1.setStatus(response.getStatus());
        apiDataRepository.save(a1);
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long startTime = (long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;

        // Record API response time using Micrometer
        System.out.println("API response time: " + timeTaken + " ms");
        System.out.println(statusResponseRepository.findApiDataByStatus());
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
