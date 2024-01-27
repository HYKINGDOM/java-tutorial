package com.java.tutorial.project.config.filter;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.java.coco.utils.TraceIDUtil;
import com.java.tutorial.project.config.filter.config.MonitorProperties;
import com.java.tutorial.project.util.Constant;
import com.java.tutorial.project.util.wrapper.HttpRequestWrapper;
import com.java.tutorial.project.util.wrapper.HttpResponseWrapper;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author meta
 */
public class HttpMonitorFilter implements Filter {

    private final Logger log = LoggerFactory.getLogger(HttpMonitorFilter.class);


    public static final String UN_KNOWN = "unknown";


    private final MonitorProperties properties;

    public HttpMonitorFilter(MonitorProperties properties) {
        this.properties = properties;
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException {
        if (!properties.isEnabled()) {
            return;
        }
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        // 跳过的url
        if (skipUrl(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }
        HttpRequestWrapper wrapperRequest = new HttpRequestWrapper(request);
        HttpResponseWrapper wrapperResponse = new HttpResponseWrapper(response);
        ServletOutputStream servletOutputStream = response.getOutputStream();
        String url = wrapperRequest.getRequestURI();
        try {
            String clientIp = getRemoteHost(wrapperRequest);
            String reqParam = getReqParam(wrapperRequest);
            String traceId = getOrGenerateTraceId(wrapperRequest);
            // 放置日志控件域对象中
            MDC.put(Constant.REQUEST_MESSAGE, reqParam);
            log.info("ip:[{}],url:[{}],traceId:[{}],reqParam:[{}]", clientIp, url, traceId, reqParam);
            long startTime = System.currentTimeMillis();
            filterChain.doFilter(wrapperRequest, wrapperResponse);
            response.setHeader(TraceIDUtil.DEFAULT_TRACE_ID, traceId);
            long elapsed = System.currentTimeMillis() - startTime;
            String res = wrapperResponse.getContent();
            log.info("ip:[{}],url:[{}],traceId:[{}],response:[{}],elapsed:[{}]ms", clientIp, url, traceId, res,
                elapsed);
            servletOutputStream.write(res.getBytes());
        } catch (Exception e) {
            filterChain.doFilter(request, response);
            log.error("HttpMonitorFilter error:", e);
        } finally {
            if (null != servletOutputStream) {
                servletOutputStream.flush();
                servletOutputStream.close();
            }
            MDC.remove(Constant.REQUEST_MESSAGE);
            TraceIDUtil.clearTraceId();
        }
    }

    /**
     * 获取目标主机的ip
     */
    private String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.isEmpty() || UN_KNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || UN_KNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || UN_KNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    /**
     * 获取traceID
     */
    private String getOrGenerateTraceId(HttpRequestWrapper wrapperRequest) {
        String traceId = wrapperRequest.getHeader(TraceIDUtil.DEFAULT_TRACE_ID);
        if (ObjectUtils.isEmpty(wrapperRequest.getHeader(TraceIDUtil.DEFAULT_TRACE_ID))) {
            traceId = TraceIDUtil.getTraceId();
            wrapperRequest.addHeader(TraceIDUtil.DEFAULT_TRACE_ID, traceId);
            return traceId;
        }
        TraceIDUtil.setTraceIdToMdcAndTtl(traceId);
        return traceId;
    }

    /**
     * 获取请求参数
     */
    private String getReqParam(HttpRequestWrapper wrapperRequest) {
        String method = wrapperRequest.getMethod();
        if ("POST".equals(method)) {
            return wrapperRequest.getBody();
        } else {
            return JSON.toJSONString(wrapperRequest.getParameterMap());
        }
    }

    /**
     * 检测是否包含url
     */
    private Boolean skipUrl(String checkVal) {
        boolean result = false;
        if (StrUtil.isEmpty(checkVal)) {
            return false;
        }
        String url = properties.getWhiteUrl();
        String[] skiUrls = url.split("\\|");
        for (String skiUrl : skiUrls) {
            if (checkVal.equals(skiUrl)) {
                result = true;
                break;
            }
        }
        if (checkVal.contains("import")) {
            return true;
        }
        return result;
    }

}
