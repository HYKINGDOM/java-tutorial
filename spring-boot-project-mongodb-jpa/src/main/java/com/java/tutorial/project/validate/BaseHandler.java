package com.java.tutorial.project.validate;

import lombok.NonNull;

/**
 * 通用责任链的 Handler
 * <p>
 * 每个业务声明一个该接口的子接口或者抽象类，再基于该接口实现对应的业务 Handler。 这样 BaseHandlerChain 可以直接注入到对应的 Handler List
 */
public interface BaseHandler<Param, Result> {

    @NonNull
    HandleResult<Result> doHandle(Param param);

    default boolean isHandler(Param param) {
        return true;
    }
}
