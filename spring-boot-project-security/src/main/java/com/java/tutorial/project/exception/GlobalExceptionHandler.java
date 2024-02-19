package com.java.tutorial.project.exception;

import com.java.tutorial.project.result.Result;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.net.ConnectException;

/**
 * @author yihur
 * @ClassName GlobalExceptionHandler
 * @Description 全局处理自定义的业务异常
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handlerNoFoundException(Exception e) {
        logger.error(e.getMessage(), e);
        return Result.error(404, "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        logger.error(e.getMessage(), e);
        return Result.error("数据库中已存在该记录");
    }

    @ExceptionHandler(UsernameIsExitedException.class)
    public Result usernameIsExitedException(UsernameIsExitedException e) {
        logger.error(e.getMessage(), e);
        return Result.error("用户已经存在");
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return Result.error();
    }

    @ExceptionHandler(ServiceException.class)
    public Result serviceException(ServiceException e) {
        logger.error(e.getMessage(), e);
        return Result.error();
    }

    @ExceptionHandler(ConnectException.class)
    public Result connectException(ConnectException e) {
        logger.error(e.getMessage(), e);
        return Result.error("系统调用异常");
    }

    @ExceptionHandler(ResourceAccessException.class)
    public Result connectException(ResourceAccessException e) {
        logger.error(e.getMessage(), e);
        return Result.error("系统之间调用异常");
    }

    @ExceptionHandler(value = {ExpiredJwtException.class})
    public Result expiredJwtException(ExpiredJwtException e) {
        logger.error(e.getMessage(), e);
        return Result.error("Token过期");
    }

    @ExceptionHandler(value = UnsupportedJwtException.class)
    public Result unsupportedJwtException(UnsupportedJwtException e) {
        logger.error(e.getMessage(), e);
        return Result.error("Token签名失败");
    }

    @ExceptionHandler(value = SignatureException.class)
    public Result signatureException(SignatureException e) {
        logger.error(e.getMessage(), e);
        return Result.error("Token格式错误");
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result illegalArgumentException(IllegalArgumentException e) {
        logger.error(e.getMessage(), e);
        return Result.error("Token非法参数异常");
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public Result accessDeniedException(AccessDeniedException e) {
        logger.error(e.getMessage(), e);
        return Result.error("Token非法参数异常");
    }

    @ExceptionHandler(value = MalformedJwtException.class)
    public Result malformedJwtException(MalformedJwtException e) {
        logger.error(e.getMessage(), e);
        return Result.error("Token没有被正确构造");
    }
}
