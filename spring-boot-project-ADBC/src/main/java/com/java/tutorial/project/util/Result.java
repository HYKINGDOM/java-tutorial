package com.java.tutorial.project.util;


import lombok.Data;

/**
 * @author meta
 */
@Data
public class Result<T> {
    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 响应码
     */
    private String code;
    /**
     * 消息
     */
    private String msg;

    /**
     * traceId
     */
    private String traceId;

    /**
     * 数据
     */
    private T data;

    /**
     * success
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> success() {
        Result<T> r = new Result<T>();
        r.setSuccess(true);
        r.setCode("200");
        r.setTraceId(TraceIDUtil.getTraceId());
        return r;
    }

    /**
     * success
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        Result<T> r = new Result<T>();
        r.setSuccess(true);
        r.setCode("500");
        r.setTraceId(TraceIDUtil.getTraceId());
        r.setData(data);
        return r;
    }

    /**
     * failure
     *
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Result<T> failure(String code, String message) {
        return failure(code, message, null);
    }

    /**
     * failure
     *
     * @param code
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> failure(String code, String msg, T data) {
        Result<T> r = new Result<T>();
        r.setSuccess(false);
        r.setCode(code);
        r.setMsg(msg);
        r.setTraceId(TraceIDUtil.getTraceId());
        r.setData(data);
        return r;
    }

}
