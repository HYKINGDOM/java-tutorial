package com.java.tutorial.project.common.config;

import java.io.InputStreamReader;
import java.util.Map;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.log.dialect.tinylog.TinyLog;
import com.java.tutorial.project.common.exception.SystemException;
import com.java.tutorial.project.common.exception.TokenException;
import lombok.extern.slf4j.Slf4j;
import org.noear.snack.ONode;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.ValidationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常信息打印
 */
@Slf4j
@Order(-1000)
@Component
public final class PrintExceptionResolver implements HandlerExceptionResolver {

    /**
     * Try to resolve the given exception that got thrown during handler execution, returning a {@link ModelAndView}
     * that represents a specific error page if appropriate.
     * <p>The returned {@code ModelAndView} may be {@linkplain ModelAndView#isEmpty() empty}
     * to indicate that the exception has been resolved successfully but that no view should be rendered, for instance
     * by setting a status code.
     *
     * @param req      current HTTP request
     * @param response current HTTP response
     * @param handler  the executed handler, or {@code null} if none chosen at the time of the exception (for example,
     *                 if multipart resolution failed)
     * @param ex       the exception that got thrown during handler execution
     * @return a corresponding {@code ModelAndView} to forward to, or {@code null} for default processing in the
     *     resolution chain
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse response, Object handler,
        Exception ex) {
        Map<String, String[]> parasMap = req.getParameterMap();
        if (parasMap.isEmpty()) {
            try (InputStreamReader isr = new InputStreamReader(req.getInputStream(), "UTF-8");) {
                StringBuilder result = new StringBuilder(4000);
                result.append("地址:=============================");
                result.append(req.getRequestURI());
                result.append("参数:=============================");
                char[] buf = new char[1024];
                for (int num; (num = isr.read(buf, 0, buf.length)) != -1; ) {
                    result.append(buf, 0, num);
                }
                result.append("方法:=============================");
                result.append(((HandlerMethod)handler).getResolvedFromHandlerMethod());
                result.append("异常:=============================");
                if (ex instanceof BindException) {
                    BindException be = (BindException)ex;
                    FieldError error = be.getBindingResult().getFieldErrors().get(0);
                    result.append(error.getField()).append("--").append(error.getDefaultMessage());
                    log.warn(String.valueOf(result));
                } else if (ex instanceof ValidationException) {
                    ValidationException ve = (ValidationException)ex;
                    result.append(ve.getMessage());
                    log.warn(String.valueOf(result));
                } else if (ex instanceof TokenException) {
                    TokenException te = (TokenException)ex;
                    result.append("未获取到RefreshToken");
                    log.warn(String.valueOf(result));
                } else if (ex instanceof SystemException) {
                    SystemException se = (SystemException)ex;
                    result.append(se.getMessage());
                    log.error(String.valueOf(result));
                } else {
                    result.append(ExceptionUtil.getMessage(ex));
                    log.error(String.valueOf(result));
                }
            } catch (Exception e) {
            }
        } else {
            try {
                StringBuilder result = new StringBuilder(4000);
                result.append("地址:=============================");
                result.append(req.getRequestURI());
                result.append("参数:=============================");
                result.append(ONode.stringify(parasMap));
                result.append("方法:=============================");
                result.append(((HandlerMethod)handler).getResolvedFromHandlerMethod());
                result.append("异常:=============================");

                if (ex instanceof BindException) {
                    BindException be = (BindException)ex;
                    FieldError error = be.getBindingResult().getFieldErrors().get(0);
                    result.append(error.getField()).append("--").append(error.getDefaultMessage());
                    log.warn(String.valueOf(result));
                } else if (ex instanceof ValidationException) {
                    ValidationException ve = (ValidationException)ex;
                    result.append(ve.getMessage());
                    log.warn(String.valueOf(result));
                } else if (ex instanceof TokenException) {
                    TokenException te = (TokenException)ex;
                    result.append("未获取到RefreshToken");
                    log.warn(String.valueOf(result));
                } else if (ex instanceof SystemException) {
                    SystemException se = (SystemException)ex;
                    result.append(se.getMessage());
                    log.error(String.valueOf(result));
                } else {
                    result.append(ExceptionUtil.getRootCauseMessage(ex));
                    log.error(String.valueOf(result));
                }
            } catch (Exception e) {
            }
        }

        return null;
    }
}
