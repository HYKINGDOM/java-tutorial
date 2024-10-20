package com.java.tutorial.project.async.callback;

import com.java.tutorial.project.async.worker.WorkResult;

/**
 * 每个执行单元执行完毕后，会回调该接口</p> 需要监听执行结果的，实现该接口即可
 *
 * @author meta
 */
@FunctionalInterface
public interface ICallback<T, V> {

    /**
     * 任务开始的监听
     */
    default void begin() {

    }

    /**
     * 耗时操作执行完毕后，就给value注入值
     */
    void result(boolean success, T param, WorkResult<V> workResult);
}
