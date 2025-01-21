package com.java.tutorial.project.util;


import cn.hutool.core.util.StrUtil;
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
        Result<T> r = new Result<>();
        r.setSuccess(true);
        r.setCode(ErrorCodeEnum.SUCCESS.getCode());
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
        Result<T> r = new Result<>();
        r.setSuccess(true);
        r.setCode(ErrorCodeEnum.SUCCESS.getCode());
        r.setMsg(ErrorCodeEnum.SUCCESS.getMsg());
        r.setTraceId(TraceIDUtil.getTraceId());
        r.setData(data);
        return r;
    }

    /**
     * failure
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(T data) {
        return error(ErrorCodeEnum.ERROR.getCode(), ErrorCodeEnum.ERROR.getMsg(), data);
    }

    /**
     * failure
     *
     * @return
     */
    public static <T> Result<T> error(String msg) {
        return error(ErrorCodeEnum.ERROR.getCode(), msg, null);
    }

    /**
     * failure
     *
     * @return
     */
    public static <T> Result<T> error() {
        return error(ErrorCodeEnum.ERROR.getCode(), ErrorCodeEnum.ERROR.getMsg(), null);
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
    public static <T> Result<T> error(String code, String msg, T data) {
        Result<T> r = new Result<>();
        r.setSuccess(false);

        // code
        if (StrUtil.isBlank(code)) {
            r.setCode(ErrorCodeEnum.ERROR.getCode());
        } else {
            r.setCode(code);
        }
        // msg
        if (StrUtil.isBlank(msg)) {
            r.setMsg(ErrorCodeEnum.ERROR.getMsg());
        } else {
            r.setMsg(msg);
        }
        r.setTraceId(TraceIDUtil.getTraceId());
        r.setData(data);
        return r;
    }

}
