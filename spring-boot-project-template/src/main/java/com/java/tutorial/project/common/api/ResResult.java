package com.java.tutorial.project.common.api;

import lombok.Getter;
import lombok.Setter;

/**
 * 通用返回对象
 *
 * @param <T>
 */
@Getter
@Setter
public class ResResult<T> {
    /**
     * 状态码
     */
    private long code;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 数据封装
     */
    private T data;

    protected ResResult() {
    }

    protected ResResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> ResResult<T> success(T data) {
        return new ResResult<T>(CommonCodeMessage.SUCCESS.getCode(), CommonCodeMessage.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data    获取的数据
     * @param message 提示信息
     */
    public static <T> ResResult<T> success(T data, String message) {
        return new ResResult<T>(CommonCodeMessage.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     */
    public static <T> ResResult<T> failed(ICodeMessage errorCode) {
        return new ResResult<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     * @param message   错误信息
     */
    public static <T> ResResult<T> failed(ICodeMessage errorCode, String message) {
        return new ResResult<T>(errorCode.getCode(), message, null);
    }

    /**
     * 失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> ResResult<T> failed(String message) {
        return new ResResult<T>(CommonCodeMessage.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> ResResult<T> failed() {
        return failed(CommonCodeMessage.FAILED);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> ResResult<T> validateFailed() {
        return failed(CommonCodeMessage.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> ResResult<T> validateFailed(String message) {
        return new ResResult<T>(CommonCodeMessage.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> ResResult<T> unauthorized(T data) {
        return new ResResult<T>(CommonCodeMessage.UNAUTHORIZED.getCode(), CommonCodeMessage.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> ResResult<T> forbidden(T data) {
        return new ResResult<T>(CommonCodeMessage.FORBIDDEN.getCode(), CommonCodeMessage.FORBIDDEN.getMessage(), data);
    }

}
