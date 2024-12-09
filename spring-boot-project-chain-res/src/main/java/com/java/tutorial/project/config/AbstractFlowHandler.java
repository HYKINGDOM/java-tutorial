package com.java.tutorial.project.config;

import com.java.tutorial.project.domain.MoneyPayVO;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author meta
 */
@Component
public abstract class AbstractFlowHandler {

    private AbstractFlowHandler nextHandler;

    /**
     * 审批
     *
     * @param param
     * @return
     */
    public abstract boolean approve(MoneyPayVO param);

    /**
     * 链路传递
     *
     * @param param
     * @return
     */
    protected boolean next(MoneyPayVO param) {
        //下一个链路没有处理器了，直接返回
        if (Objects.isNull(nextHandler)) {
            return true;
        }

        //执行下一个处理器
        return nextHandler.approve(param);
    }

}
