package com.java.validate.util;

import java.util.HashMap;
import java.util.Objects;


/**
 * 操作消息提醒
 *
 * @author yihur
 */
public class ResultVo extends HashMap<String, Object> {

    public static final String ERROR_MESSAGE_HEADER = "Error Message: ";

    public static final int HTTP_STATUS_SUCCESS = 200;

    public static final int HTTP_STATUS_ERROR = 500;

    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    public static final String CODE_TAG = "code";

    /**
     * 返回内容
     */
    public static final String MSG_TAG = "msg";

    /**
     * 数据对象
     */
    public static final String DATA_TAG = "data";

    /**
     * 初始化一个新创建的  ResultVo 对象，使其表示一个空消息。
     */
    public ResultVo() {

    }

    /**
     * 初始化一个新创建的  ResultVo 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     */
    public ResultVo(int code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * todo 对data进行判断 或者在msg中加入判断条件,触发时data和msg 返回结果相同
     * 初始化一个新创建的  ResultVo 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     */
    public ResultVo(int code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (Objects.nonNull(data)) {
            super.put(DATA_TAG, data);
        }
        if (msg.contains(ERROR_MESSAGE_HEADER)) {
            super.put(DATA_TAG, msg);
        }
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static ResultVo success() {
        return ResultVo.success("操作成功");
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static ResultVo success(Object data) {
        return ResultVo.success("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static ResultVo success(String msg) {
        return ResultVo.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static ResultVo success(String msg, Object data) {
        return new ResultVo(HTTP_STATUS_SUCCESS, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static ResultVo error() {
        return ResultVo.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static ResultVo error(String msg) {
        return ResultVo.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static ResultVo error(String msg, Object data) {
        return new ResultVo(HTTP_STATUS_ERROR, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 警告消息
     */
    public static ResultVo error(int code, String msg) {
        return new ResultVo(code, msg, null);
    }
}

