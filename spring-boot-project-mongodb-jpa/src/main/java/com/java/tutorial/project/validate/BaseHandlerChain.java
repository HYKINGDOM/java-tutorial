package com.java.tutorial.project.validate;

import lombok.Getter;

import java.util.List;

/**
 * 通用责任链模式
 * <p>
 * 使用方法:
 * <p>
 * 1. 创建一个对应业务的责任链控制器, 继承 BaseHandlerChain， 如:
 * {@code MyHandlerChain extends BaseHandlerChain<MyHandler, MyParam, MyResult>}
 * <p>
 * 2. 创建一个对应业务的责任链处理器 Handler，继承 BaseHandler， 如: {@code MyHandler extends BaseHandler<MyParam, MyResult>}
 * <p>
 * 3. 编写业务需要的处理器 Handler 实现 MyHandler 接口的 doHandle 方法。推荐把控制器和处理器都交给 Spring 控制，可以直接注入。
 *
 * @author HY
 */
public class BaseHandlerChain<Handler extends BaseHandler<Param, Result>, Param, Result> {

    @Getter
    private final List<Handler> handlerList;

    public BaseHandlerChain(List<Handler> handlerList) {
        this.handlerList = handlerList;
    }

    public Result handleChain(Param param) {
        for (Handler handler : handlerList) {
            if (!handler.isHandler(param)) {
                continue;
            }
            HandleResult<Result> result = handler.doHandle(param);
            if (result.isNext()) {
                continue;
            }
            return result.getData();
        }

        return null;
    }
}

