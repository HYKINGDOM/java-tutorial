package com.java.tutorial.project.config;

import com.java.coco.utils.TraceIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author meta
 */
@Order(Integer.MIN_VALUE)
@Slf4j
@Component
public class MdcInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        response.setHeader(TraceIDUtil.DEFAULT_TRACE_ID, TraceIDUtil.getTraceId());
        return true;
    }

    /**
     * @param request
     * @param response
     * @param handler
     * @param ex
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
        Exception ex) {
        TraceIDUtil.clearTraceId();
    }
}
