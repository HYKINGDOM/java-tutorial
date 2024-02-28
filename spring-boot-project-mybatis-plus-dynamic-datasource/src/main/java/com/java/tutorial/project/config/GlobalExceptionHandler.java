package com.java.tutorial.project.config;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.google.common.base.Throwables;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

import com.java.tutorial.project.util.Result;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;

import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * 全局异常处理器
 *
 * @author kscs
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Value("${spring.servlet.multipart.max-file-size}")
    private String fileSize;

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 权限校验异常
     */
//    @ExceptionHandler(AccessDeniedException.class)
//    public Result handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request) {
//        log.error("请求地址'{}',权限校验失败'{}'", request.getRequestURI(), e.getMessage());
//        return Result.error(HttpStatus.FORBIDDEN, "没有权限, 请联系管理员授权!");
//    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
        HttpServletRequest request) {
        log.error("请求地址'{}',不支持'{}'请求", request.getRequestURI(), e.getMethod());
        return Result.error(e.getMessage());
    }

//    /**
//     * 业务异常
//     */
//    @ExceptionHandler(ServiceException.class)
//    public Result handleServiceException(ServiceException e, HttpServletRequest request) {
//        log.error("RequestURI :{}, Error Message :{} , Error stack :{}", request.getRequestURI(), e.getMessage(),
//            Throwables.getStackTraceAsString(e));
//        Integer code = e.getCode();
//        return StringUtils.isNotNull(code) ? Result.error(code, e.getMessage()) : Result.error(e.getMessage());
//    }

    /**
     * 此异常不会打印异常堆栈 是已知的业务API异常
     */
//    @ExceptionHandler(BusinessApiException.class)
//    public Result handleBusinessApiException(BusinessApiException e, HttpServletRequest request) {
//        log.error("RequestURI :{}, Error Message :{}", request.getRequestURI(), e.getMessage());
//        Integer code = e.getCode();
//        return StringUtils.isNotNull(code) ? Result.error(code, e.getMessage()) : Result.error(e.getMessage());
//    }

    /**
     * 请求路径中缺少必需的路径变量
     */
    @ExceptionHandler(MissingPathVariableException.class)
    public Result handleMissingPathVariableException(MissingPathVariableException e, HttpServletRequest request) {
        log.error("请求路径中缺少必需的路径变量'{}',发生缺少必需的路径变量异常.", request.getRequestURI());
        return Result.error(String.format("请求路径中缺少必需的路径变量[%s]", e.getVariableName()));
    }

    /**
     * 请求参数类型不匹配
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e,
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
    public Result handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        log.error("请求地址'{}',发生运行时异常: {}.", request.getRequestURI(), Throwables.getStackTraceAsString(e));
        return Result.error(500, "发生运行时异常, 请联系管理员!");
    }

    /**
     * 必填参数异常处理
     *
     * @param e
     * @param request
     *
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result handleHttpMessageNotReadableException(HttpMessageNotReadableException e,
        HttpServletRequest request) {
        log.error("请求地址'{}',请求参数异常: {}.", request.getRequestURI(), Throwables.getStackTraceAsString(e));
        String erroMessage = null;
        Throwable cause = e.getCause();
        if (cause instanceof JsonMappingException) {
            List<Reference> list = ((JsonMappingException)cause).getPath();
            for (Reference r : list) {
                Object object = r.getFrom();
                Class<?> c = object.getClass();
                String fieldName = r.getFieldName();
                Field field;
                try {
                    field = c.getDeclaredField(fieldName);
                    JsonFormatValid declaredAnnotation = field.getDeclaredAnnotation(JsonFormatValid.class);
                    if (null != declaredAnnotation) {
                        erroMessage = declaredAnnotation.message();
                    }
                } catch (NoSuchFieldException noSuchFieldException) {
                    log.error("JsonFormatValid 异常:{}", noSuchFieldException.getMessage());
                }
                if (erroMessage != null) {
                    break;
                }
            }
        }
        return Result.error(500,
            Optional.ofNullable(erroMessage).orElse("请求参数格式异常!请检查参数!"));
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e, HttpServletRequest request) {
        log.error("请求地址'{}',发生系统异常: {}.", request.getRequestURI(), Throwables.getStackTraceAsString(e));
        return Result.error(500, "服务异常, 请联系管理员!");
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public Result handleBindException(BindException e) {
        String message = e.getAllErrors().get(0).getDefaultMessage();
        log.error(message);
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

    /**
     * 主键或UNIQUE索引，数据重复异常
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',数据库中已存在记录'{}'", requestURI, e.getMessage());
        return Result.error("数据库中已存在该记录，请联系管理员确认");
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result handleMissingServletRequestParameterException(MissingServletRequestParameterException e,
        HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',缺少请求参数", requestURI);
        return Result.error("缺少请求参数!");
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Result handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e,
        HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',文件大小已经超过限制: {}, 当前限制: {}", requestURI, e.getMessage(), fileSize);
        return Result.error("文件大小已经超过 " + fileSize + "限制!");
    }

    /**
     * Mybatis系统异常 通用处理
     */
    @ExceptionHandler(MyBatisSystemException.class)
    public Result handleCannotFindDataSourceException(MyBatisSystemException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String message = e.getMessage();
        if (null != message && message.contains("CannotFindDataSourceException")) {
            log.error("请求地址'{}', 未找到数据源", requestURI);
            return Result.error("未找到数据源，请联系管理员确认");
        }
        log.error("请求地址'{}', Mybatis系统异常", requestURI, e);
        return Result.error(message);
    }
}
