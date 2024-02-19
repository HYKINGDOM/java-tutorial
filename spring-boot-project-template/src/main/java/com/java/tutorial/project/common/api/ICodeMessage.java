package com.java.tutorial.project.common.api;

/**
 * 常用API返回对象接口
 *
 * @author HY
 */
public interface ICodeMessage {
    /**
     * 返回码
     *
     * @return 返回状态吗
     */
    long getCode();

    /**
     * 返回信息
     *
     * @return 返回提示信息
     */
    String getMessage();
}
