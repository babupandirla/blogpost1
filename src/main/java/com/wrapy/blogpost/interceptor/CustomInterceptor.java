package com.wrapy.blogpost.interceptor;

import com.wrapy.blogpost.entity.ApiData;
import com.wrapy.blogpost.payload.StatusResponse;
import com.wrapy.blogpost.repositories.ApiDataRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class CustomInterceptor implements HandlerInterceptor {
    @Autowired
    ApiDataRepository apiDataRepository;
    @PersistenceContext
    private EntityManager em;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        request.setAttribute("startTime",LocalDateTime.now());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println(response.getStatus());
        System.out.println(request.getRequestURL());

        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LocalDateTime localUTCDT = (LocalDateTime) request.getAttribute("startTime");
        long startTime = localUTCDT.atZone(ZoneId.of("UTC")).toInstant().toEpochMilli();
//        long startTime = (long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        ApiData a1=new ApiData();
        a1.setUrl(request.getRequestURL().toString());
        a1.setStatus(response.getStatus());
        a1.setStartDate((LocalDateTime) request.getAttribute("startTime"));
        a1.setEndDate(LocalDateTime.now());
        a1.setExecutionTime(timeTaken);
        apiDataRepository.save(a1);
        // Record API response time using Micrometer
        System.out.println("API response time: " + timeTaken + " ms");
        List<Object> resultList = em.createNativeQuery("SELECT count(*), ap.status FROM api_data  ap GROUP BY ap.status" ).getResultList();
        for(Object a:resultList){
            System.out.println(a.toString());
        }
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
