package com.java.tutorial.project.config;

import com.google.common.base.Throwables;
import com.java.tutorial.project.common.util.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.Optional;

/**
 * 全局异常处理器
 *
 * @author meta
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Value("${spring.servlet.multipart.max-file-size}")
    private String fileSize;

    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<String> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
        HttpServletRequest request) {
        log.error("请求地址'{}',不支持'{}'请求", request.getRequestURI(), e.getMethod());
        return Result.error(e.getMessage());
    }

    /**
     * 请求路径中缺少必需的路径变量
     */
    @ExceptionHandler(MissingPathVariableException.class)
    public Result<String> handleMissingPathVariableException(MissingPathVariableException e,
        HttpServletRequest request) {
        log.error("请求路径中缺少必需的路径变量'{}',发生缺少必需的路径变量异常.", request.getRequestURI());
        return Result.error(String.format("请求路径中缺少必需的路径变量[%s]", e.getVariableName()));
    }

    /**
     * 请求参数类型不匹配
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e,
        HttpServletRequest request) {
        log.error("请求参数类型不匹配'{}',发生参数类型不匹配异常: {}.", request.getRequestURI(),
            Throwables.getStackTraceAsString(e));
        return Result.error(String.format("请求参数类型不匹配，参数[%s]要求类型为：'%s'，但输入值为：'%s'", e.getName(),
            Optional.ofNullable(e.getRequiredType()).map(Class::getName).orElse(""), e.getValue()));
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public Result<String> handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        log.error("请求地址'{}',发生运行时异常: {}.", request.getRequestURI(), Throwables.getStackTraceAsString(e));
        return Result.error("发生运行时异常, 请联系管理员!");
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e, HttpServletRequest request) {
        log.error("请求地址'{}',发生系统异常: {}.", request.getRequestURI(), Throwables.getStackTraceAsString(e));
        return Result.error("服务异常, 请联系管理员!");
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public Result<String> handleBindException(BindException e) {
        String message = e.getAllErrors().get(0).getDefaultMessage();
        log.error("自定义验证异常:{}", message);
        return Result.error(message);
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = Optional.ofNullable(e.getBindingResult().getFieldError())
            .map(DefaultMessageSourceResolvable::getDefaultMessage).orElse("");
        log.error(message);
        return Result.error(message);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result<String> handleMissingServletRequestParameterException(MissingServletRequestParameterException e,
        HttpServletRequest request) {
        String url = request.getRequestURI();
        log.error("请求地址'{}',缺少请求参数", url);
        return Result.error("缺少请求参数!");
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Result<String> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e,
        HttpServletRequest request) {
        String url = request.getRequestURI();
        log.error("请求地址'{}',文件大小已经超过限制: {}, 当前限制: {}", url, e.getMessage(), fileSize);
        return Result.error("文件大小已经超过 " + fileSize + "限制!");
    }

}
