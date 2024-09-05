package com.java.tutorial.project.config.filter;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.java.coco.utils.TraceIDUtil;
import com.java.tutorial.project.config.filter.wrapper.HttpRequestWrapper;
import com.java.tutorial.project.config.filter.wrapper.HttpResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

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
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author meta
 */
@Slf4j
public class HttpMonitorFilter implements Filter {

    public static final String UN_KNOWN = "unknown";
    public static final String REQUEST_MESSAGE = "request_message";
    public static final String DEFAULT_IP = "0.0.0.0";
    public static final String DEFAULT_IP_V6 = "0:0:0:0:0:0:0:1";

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
            String traceId = getOrGenerateTraceId(wrapperRequest);
            String clientIp = getRemoteHost(wrapperRequest);
            String reqParam = getReqParam(wrapperRequest);
            // 放置日志控件域对象中
            MDC.put(REQUEST_MESSAGE, reqParam);
            log.info("ip:[{}],url:[{}],reqParam:[{}]", clientIp, url, reqParam);
            long startTime = System.currentTimeMillis();
            filterChain.doFilter(wrapperRequest, wrapperResponse);
            response.setHeader(TraceIDUtil.DEFAULT_TRACE_ID, traceId);
            long elapsed = System.currentTimeMillis() - startTime;
            String res = wrapperResponse.getContent();
            log.info("ip:[{}],url:[{}],response:[{}],elapsed:[{}]ms", clientIp, url, res, elapsed);
            servletOutputStream.write(res.getBytes());
        } catch (Exception e) {
            log.error("HttpMonitorFilter error:", e);
        } finally {
            if (null != servletOutputStream) {
                servletOutputStream.flush();
                servletOutputStream.close();
            }
            MDC.remove(REQUEST_MESSAGE);
            TraceIDUtil.clearTraceId();
        }
    }

    private String getRemoteHost(HttpServletRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("HttpServletRequest object cannot be null");
        }

        String ipFromForwardedFor = request.getHeader("x-forwarded-for");
        String ipFromProxyClientIp = request.getHeader("Proxy-Client-IP");
        String ipFromWlProxyClientIp = request.getHeader("WL-Proxy-Client-IP");
        String remoteAddr = request.getRemoteAddr();

        //返回第一个非blank 元素
        String ip = StrUtil.firstNonBlank(ipFromForwardedFor, ipFromProxyClientIp, ipFromWlProxyClientIp, remoteAddr);

        // 验证 IP 地址格式
        if (DEFAULT_IP_V6.equals(ip)) {
            ip = "127.0.0.1";
        } else if (!isValidIp(ip)) {
            // 如果 IP 地址无效，则使用默认值
            ip = DEFAULT_IP;
        }

        return ip;
    }

    /**
     * 验证 IP 地址是否有效
     *
     * @param ip
     * @return
     */
    private boolean isValidIp(String ip) {
        try {
            InetAddress inetAddress = InetAddress.getByName(ip);
            return !inetAddress.isAnyLocalAddress() && !inetAddress.isLoopbackAddress();
        } catch (UnknownHostException e) {
            return false;
        }
    }

    private String getOrGenerateTraceId(HttpRequestWrapper wrapperRequest) {
        String traceId = wrapperRequest.getHeader(TraceIDUtil.DEFAULT_TRACE_ID);
        if (null == traceId) {
            traceId = TraceIDUtil.getTraceId();
            wrapperRequest.addHeader(TraceIDUtil.DEFAULT_TRACE_ID, traceId);
            return traceId;
        }
        return traceId;
    }

    private String getReqParam(HttpRequestWrapper wrapperRequest) {

        try {
            // 添加查询参数
            Map<String, Object> params = new HashMap<>(wrapperRequest.getParameterMap());

            // 添加头部信息
            Enumeration<String> headers = wrapperRequest.getHeaders(TraceIDUtil.DEFAULT_TRACE_ID);
            if (headers != null) {
                params.put(TraceIDUtil.DEFAULT_TRACE_ID, headers);
            }

            // 尝试获取请求体
            String body = wrapperRequest.getBody();
            if (null != body) {
                // 将所有参数合并为 JSON 字符串
                params.put("body", body);
            }

            return JSON.toJSONString(params);
        } catch (Exception e) {
            // 异常处理，可以选择记录日志或者返回默认值
            System.err.println("Error retrieving request body: " + e.getMessage());
            return "";
        }
    }

    private Boolean skipUrl(String checkVal) {
        if (isUrlInWhiteList(checkVal)) {
            return true;
        }
        return isImportKeywordPresent(checkVal);
    }

    private Boolean isUrlInWhiteList(String checkVal) {
        String url = properties.getFilterWhiteUrl();
        String[] skiUrls = url.split("\\|");
        for (String skiUrl : skiUrls) {
            if (checkVal.equals(skiUrl)) {
                return true;
            }
        }
        return false;
    }

    private Boolean isImportKeywordPresent(String checkVal) {
        return checkVal.matches(".*import.*");
    }

}
