package com.java.tutorial.project.config;

import com.java.tutorial.project.util.TraceIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请求包装器的使用
 * @author kscs
 */
@Slf4j
@Component
public class RequestWrapperFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        TraceIDUtil.getTraceId();
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        // 可以在这里处理请求数据
        byte[] body = requestWrapper.getContentAsByteArray();
        // 处理body，例如记录日志
        log.info("Request body: {}", new String(body));
        filterChain.doFilter(requestWrapper, response);
    }
}
