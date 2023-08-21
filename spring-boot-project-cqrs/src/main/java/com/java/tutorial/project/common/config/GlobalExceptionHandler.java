package com.java.tutorial.project.common.config;

import com.java.tutorial.project.common.exception.SystemException;
import com.java.tutorial.project.common.exception.TokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import jakarta.validation.ConstraintViolationException;

/**
 *
 * 全局异常处理器
 *
 */
@RestControllerAdvice
public final class GlobalExceptionHandler {

	/**
	 * 业务异常
	 */
	@ExceptionHandler(SystemException.class)
	public ResponseEntity<String> systemException(SystemException ex) {
		return ResponseEntity
				.status(HttpStatus.NOT_EXTENDED)
				.header("Content-Type", "text/plain;charset=UTF-8")
				.body(ex.getMessage());
	}

	/**
	 * 基本类型校验异常
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> bindException(ConstraintViolationException ex) {
		String error = ex.getConstraintViolations().stream().findFirst().map(cv -> cv.getMessage()).get();

		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.header("Content-Type", "text/plain;charset=UTF-8")
				.body(error);
	}

	/**
	 * 实体类型校验异常
	 */
	@ExceptionHandler(BindException.class)
	public ResponseEntity<String> bindException(BindException ex) {
		FieldError error = ex.getBindingResult().getFieldErrors().get(0);

		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.header("Content-Type", "text/plain;charset=UTF-8")
				.body(error.getDefaultMessage());
	}

	/**
	 * 令牌刷新值过期
	 */
	@ExceptionHandler(TokenException.class)
	public ResponseEntity<Void> tokenException(TokenException ex) {
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
	}

	/**
	 * 其他异常
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> otherException(Exception ex) {
		return ResponseEntity
				.status(HttpStatus.NOT_EXTENDED)
				.header("Content-Type", "text/plain;charset=UTF-8")
				.body("服务器异常");
	}

}
