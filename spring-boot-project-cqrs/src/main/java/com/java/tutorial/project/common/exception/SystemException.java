package com.java.tutorial.project.common.exception;

/**
 *
 * 业务异常(建议用系统名字来命名业务异常)
 *
 */
public final class SystemException extends RuntimeException {

	public SystemException(String message) {
		super(message);
	}

}
